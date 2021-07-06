package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.AuthorizationModel;
import kg.ItAcademy.mobilepsychology.model.UserModel;

import java.util.List;

public interface UserService {
    User saveWithPasswordEncode(UserModel userModel);
    User save(User user);
    List<User> getAllUsers();
    User findById(Long id) throws ObjectNotFoundException;
    User findByUsername(String username) throws ObjectNotFoundException;
    String getTokenByAuthModel(AuthorizationModel authModel);
    void deleteById(Long id);
    User changeStatusById(Long userId);
    User findUserByUsername(String username);
}
