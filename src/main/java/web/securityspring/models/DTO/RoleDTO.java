package web.securityspring.models.DTO;


import web.securityspring.models.User;

import java.io.Serializable;
import java.util.Set;

public class RoleDTO implements Serializable {
    private long id;
    private String roleName;
    private Set<User> users;

    public RoleDTO(long id, String roleName, Set<User> users) {
        this.id = id;
        this.roleName = roleName;
        this.users = users;
    }
}
