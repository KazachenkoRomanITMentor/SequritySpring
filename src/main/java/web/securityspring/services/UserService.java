package web.securityspring.services;

import web.securityspring.models.User;
import java.util.List;
import java.util.Optional;


public interface UserService {
    User addUser(User user);
    Optional<User> findUserById(long id);
    List<User> getAllUser();
    void deleteUserById(long id);

    User findUserByName(String name);

    User updateUser(User user);

}
