package web.securityspring.services;

import web.securityspring.models.Role;

import java.util.Set;

public interface RoleService {

    Role addNewRole(Role role);
    void deleteRole(Role role);
    Set<Role> getAllRoles();
}
