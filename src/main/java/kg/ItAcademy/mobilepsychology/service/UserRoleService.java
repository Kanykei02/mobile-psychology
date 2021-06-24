package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.UserRole;
import kg.ItAcademy.mobilepsychology.model.RoleModel;

import java.util.List;

public interface UserRoleService {
    UserRole save(UserRole userRole);
    UserRole save(RoleModel userRoleModel);
    List<UserRole> getAllRoles();
    UserRole findById(Long id);
    void deleteById(Long id);
    UserRole getUserRoleByUserId(Long id);
}
