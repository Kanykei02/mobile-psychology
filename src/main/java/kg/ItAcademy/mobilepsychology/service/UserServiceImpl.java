package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.Picture;
import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.entity.UserRole;
import kg.ItAcademy.mobilepsychology.exception.AuthorizationException;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.AuthorizationModel;
import kg.ItAcademy.mobilepsychology.model.UserModel;
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

    @Autowired
    private PictureService pictureService;

    @Override
    public User saveWithPasswordEncode(UserModel userModel) {
        Optional<User> userLoginCheck = userRepository.findByUsername(userModel.getUsername());
        Optional<User> userEmailCheck = userRepository.findByEmail(userModel.getEmail());
        Picture picture = pictureService.findById(userModel.getProfilePicture());

        if(userLoginCheck.isPresent()){
            throw new AuthorizationException("This username already exists!");
        } else if (userEmailCheck.isPresent()) {
            throw new AuthorizationException("Enter a different Email Address!");
        } else {
            User user1 = User.builder()
                    .biography(userModel.getBiography())
                    .dateOfBirth(userModel.getDateOfBirth())
                    .email(userModel.getEmail())
                    .fullName(userModel.getFullName())
                    .gender(userModel.getGender())
                    .username(userModel.getUsername())
                    .profilePicture(picture)
                    .build();
            user1.setStatus(1L);
            user1.setPassword(passwordEncoder.encode(userModel.getPassword()));
            userRepository.save(user1);
            UserRole userRole = new UserRole();
            userRole.setRoleName("ROLE_USER");
            userRole.setUser(user1);
            userRoleService.save(userRole);
            return userRepository.save(user1);
        }
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public String getTokenByAuthModel(AuthorizationModel authorizationModel) {
        String authResult = "";
        System.out.println("test1");
        User user = findByUsername(authorizationModel.getUsername());
        if(user == null) authResult = "Invalid username/password!";
        else {
            if(passwordEncoder.matches(authorizationModel.getPassword(), user.getPassword())) {
                String loginPassPair = user.getUsername() + ":" + authorizationModel.getPassword();
                authResult = "Basic " + Base64.getEncoder().encodeToString(loginPassPair.getBytes());
            } else authResult = "Invalid username/password!";
        }
        System.out.println("test2");
        return authResult;
    }

    @Override
    public List<User> getAllUsers(){
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getName());
        return userRepository.findAll();
    }

    @Override
    public User findById(Long id) throws ObjectNotFoundException {
        return userRepository.findById(id).orElseThrow(()-> new ObjectNotFoundException("Object not found: ", id));
    }

    @Override
    public User findByUsername(String username) throws ObjectNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new ObjectNotFoundException("User not found: ", username));
    }

    @Override
    public void deleteById(Long id){
        UserRole userRoleByUserId = userRoleService.getUserRoleByUserId(id);
        userRoleService.deleteById(userRoleByUserId.getId());
        userRepository.deleteById(id);
    }

    @Override
    public User changeStatusById(Long userId) {
        User user1 = findById(userId);
        if(user1 == null);
        else {
            if (user1.getStatus() == 1) {
                user1.setStatus(0l);
            } else if (user1.getStatus() == 0) {
                user1.setStatus(1l);
            }
        }
        return save(user1);
    }
}
