package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.exception.AuthorizationException;
import kg.ItAcademy.mobilepsychology.model.AuthorizationModel;
import kg.ItAcademy.mobilepsychology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity save(@RequestBody User user) throws AuthorizationException {
        try {
            User user1 = userService.saveWithPasswordEncode(user);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PostMapping("/sign-in")
    public String getToken(@RequestBody AuthorizationModel authorizationModel) {
        return userService.getTokenByAuthModel(authorizationModel);
    }

    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    @DeleteMapping("/{userId}")
    public void deleteById(@PathVariable Long userId){
        userService.deleteById(userId);
    }

    @GetMapping("/{userId}")
    public User getById(@PathVariable Long userId){
        return userService.findById(userId);
    }
}
