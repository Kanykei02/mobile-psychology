package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.exception.AuthorizationException;
import kg.ItAcademy.mobilepsychology.exception.ObjectNotFoundException;
import kg.ItAcademy.mobilepsychology.model.AuthorizationModel;
import kg.ItAcademy.mobilepsychology.model.UserModel;
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
    public ResponseEntity save(@RequestBody UserModel user) {
        try {
            User user1 = userService.saveWithPasswordEncode(user);
            return new ResponseEntity<>(user1, HttpStatus.OK);
        } catch (AuthorizationException e){
            System.out.println(e.getMessage());
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
    public ResponseEntity getById(@PathVariable Long userId) throws ObjectNotFoundException{
        try {
            User userListId = userService.findById(userId);
            return new ResponseEntity<>(userListId, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }

    @PatchMapping("/{userId}")
    public User changeUserStatusById(@PathVariable Long userId) {
        return userService.changeStatusById(userId);
    }

    @PostMapping("/{username}")
    public ResponseEntity findUserByUsername(@PathVariable String username){
        try {
            User userListUsername = userService.findByUsername(username);
            return new ResponseEntity<>(userListUsername, HttpStatus.OK);
        } catch (Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.OK);
        }
    }
}
