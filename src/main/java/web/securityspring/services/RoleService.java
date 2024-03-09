package web.securityspring.services;

import web.securityspring.models.Role;

import java.util.Set;

public interface RoleService {
    Set<Role> getAllRoles();
}
