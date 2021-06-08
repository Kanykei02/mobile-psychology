package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.model.AuthorizationModel;

import java.util.List;

public interface UserService {
    User saveWithPasswordEncode(User user);
    User save(User user);
    List<User> getAllUsers();
    User findById(Long id);
    User findByUsername(String username);
    String getTokenByAuthModel(AuthorizationModel authModel);
    User deleteById(Long id);
}
