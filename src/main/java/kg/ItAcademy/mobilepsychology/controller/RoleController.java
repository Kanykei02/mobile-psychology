package kg.ItAcademy.mobilepsychology.controller;

import kg.ItAcademy.mobilepsychology.entity.UserRole;
import kg.ItAcademy.mobilepsychology.model.RoleModel;
import kg.ItAcademy.mobilepsychology.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/roles")
public class RoleController {
    @Autowired
    private UserRoleService userRoleService;

    @PostMapping
    public UserRole createOrUpdate(@RequestBody RoleModel roleModel){
        return userRoleService.save(roleModel);
    }

    @GetMapping
    public List<UserRole> getAllRoles(){
        return userRoleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public UserRole getById(@PathVariable Long id){
        return userRoleService.findById(id);
    }

    @DeleteMapping("/{id}")
    public UserRole deleteById(@PathVariable Long id){
        return userRoleService.deleteById(id);
    }
}
