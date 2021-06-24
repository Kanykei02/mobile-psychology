package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.entity.UserRole;
import kg.ItAcademy.mobilepsychology.exception.AuthorizationException;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.AuthorizationModel;
import kg.ItAcademy.mobilepsychology.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRoleService userRoleService;

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public User saveWithPasswordEncode(User user) {
        Optional<User> userLoginCheck = userRepository.findByUsername(user.getUsername());
        Optional<User> userEmailCheck = userRepository.findByEmail(user.getEmail());

        if(userLoginCheck.isPresent()){
            throw new AuthorizationException("Ошибка!");
        } else if (userEmailCheck.isPresent()) {
            throw new AuthorizationException("Ошибка");
        } else {
            user.setStatus(1L);
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userRepository.save(user);
            UserRole userRole = new UserRole();
            userRole.setRoleName("ROLE_USER");
            userRole.setUser(user);
            userRoleService.save(userRole);
            return user;
        }
    }

    @Override
    public String getTokenByAuthModel(AuthorizationModel authorizationModel) {
        String authResult = "";
        User user = findByUsername(authorizationModel.getUsername());
        if(user == null) authResult = "Неверный логин/пароль";
        else {
            if(passwordEncoder.matches(authorizationModel.getPassword(), user.getPassword())) {
                String loginPassPair = user.getUsername() + ":" + authorizationModel.getPassword();
                authResult = "Basic " + Base64.getEncoder().encodeToString(loginPassPair.getBytes());
            } else authResult = "Неверный логин/пароль";
        }
        return authResult;
    }

    @Override
    public List<User> getAllUsers(){
        System.out.println("Пользователь: " + SecurityContextHolder.getContext().getAuthentication().getName());
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws ObjectNotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Пользователь не найден: ", id));
    }

    @Override
    public User findByUsername(String username) throws ObjectNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("Пользователь не найден: ", username));
    }

    @Override
    public void deleteById(Long id){
        UserRole userRoleByUserId = userRoleService.getUserRoleByUserId(id);
        userRoleService.deleteById(userRoleByUserId.getId());
        userRepository.deleteById(id);
    }

    @Override
    public User changeStatusById(Long userId) throws ObjectNotFoundException {
        User user1 = findById(userId);

        if(user1 == null) return null;

        if (user1.getStatus() == 1) {
            user1.setStatus(0l);
        } else if (user1.getStatus() == 0) {
            user1.setStatus(1l);
        }

        return save(user1);
    }
}
