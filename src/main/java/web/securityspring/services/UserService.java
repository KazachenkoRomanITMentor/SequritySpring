package web.securityspring.services;

import web.securityspring.models.Role;
import web.securityspring.models.User;

import java.util.List;
import java.util.Optional;
import java.util.Set;

public interface UserService {
    User addUser(User user);
    void deleteUser(User user);
    User findUserById(long id);
    List<User> getAllUser();
    void deleteUserById(long id);
    Set<Role> getUserRole(Set<String> rolesId);

}
