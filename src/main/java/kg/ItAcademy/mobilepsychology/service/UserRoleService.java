package kg.ItAcademy.mobilepsychology.service;

import kg.ItAcademy.mobilepsychology.entity.UserRole;
import kg.ItAcademy.mobilepsychology.model.RoleModel;

public interface UserRoleService {
    UserRole save(UserRole userRole);
    UserRole save(RoleModel userRoleModel);
    UserRole findById(Long id);
}
