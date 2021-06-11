package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.User;
import kg.ItAcademy.mobilepsychology.model.AuthorizationModel;
import kg.ItAcademy.mobilepsychology.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthorizationController {
    @Autowired
    private UserService userService;

    @PostMapping("/sign-up")
    public ResponseEntity save(@RequestBody User user) throws Exception {
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
}
