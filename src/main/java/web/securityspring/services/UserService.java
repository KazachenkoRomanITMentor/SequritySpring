package web.securityspring.services;

import web.securityspring.models.User;
import java.util.List;



public interface UserService {
    User addUser(User user);
    User findUserById(long id);
    List<User> getAllUser();
    void deleteUserById(long id);

    User findUserByName(String name);

    User updateUser(User user);

}
