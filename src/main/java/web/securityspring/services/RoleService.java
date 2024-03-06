package web.securityspring.services;

import web.securityspring.models.Role;

import java.util.List;

public interface RoleService {

    Role addNewRole(Role role);
    void deleteRole(Role role);
    List<Role> getAllRoles();
}
