package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.entity.UserRole;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.RoleModel;
import kg.ItAcademy.mobilepsychology.model.UserModel;
import kg.ItAcademy.mobilepsychology.repository.RoleRepository;
import org.hibernate.ObjectDeletedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserRoleServiceImpl implements UserRoleService{

    @Autowired
    private RoleRepository userRoleRepository;

    @Autowired
    private UserService userService;

    @Override
    public UserRole save(UserRole userRole) {
        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole save(RoleModel userRoleModel) {
        UserRole userRole = new UserRole();
        userRole.setRoleName(userRoleModel.getRoleName());
        User user = userService.findById(userRoleModel.getUserId());

        if(user == null) throw new IllegalArgumentException("User with this ID " + userRoleModel.getUserId() + " not found");
        userRole.setUser(user);

        return userRoleRepository.save(userRole);
    }

    @Override
    public UserRole changeUserRoleById(Long id, RoleModel userId) {
        UserRole userRole = findById(id);
        User user = userService.findById(userId.getUserId());
        if(user == null) throw new IllegalArgumentException("User not found");
        else {
            if(userRole.getRoleName().equals("ROLE_USER")){
                userRole.setRoleName("ROLE_PSYCHOLOGIST");
                userRole.setUser(user);
            } else if (userRole.getRoleName().equals("ROLE_PSYCHOLOGIST")){
                userRole.setRoleName("ROLE_USER");
                userRole.setUser(user);
            }
        }
        return userRoleRepository.save(userRole);
    }

    @Override
    public List<UserRole> getAllRoles(){
        return userRoleRepository.findAll();
    }

    @Override
    public UserRole findById(Long id) throws ObjectDeletedException {
        return userRoleRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Role not found: ", id));
    }

    @Override
    public void deleteById(Long id) {
        userRoleRepository.deleteById(id);
    }

    @Override
    public UserRole getUserRoleByUserId(Long id) {
        return userRoleRepository.findByUserId(id);
    }
}
