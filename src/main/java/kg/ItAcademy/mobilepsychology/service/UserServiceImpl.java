package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.entity.UserRole;
import kg.ItAcademy.mobilepsychology.exception.AuthorizationException;
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
            throw new AuthorizationException("Такой логин уже существует!");
        } else if (userEmailCheck.isPresent()) {
            throw new AuthorizationException("Введите другой Email");
        } else {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user = userRepository.save(user);
            UserRole userRole = new UserRole();
            userRole.setRoleName("ROLE_USER");
            userRole.setUser(user);
            userRoleService.save(userRole);
            User user1 = new User();
            user1.setStatus(1L);
            userRepository.save(user1);
            return user;
        }
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElse(null);
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
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User deleteById(Long id){
        User user = findById(id);
        if (user != null){
            userRepository.delete(user);
            return user;
        }
        return null;
    }
}
