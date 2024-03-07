package web.securityspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.securityspring.models.Role;
import web.securityspring.models.User;
import web.securityspring.repository.UserRepository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.saveAndFlush(user);
    }

    @Override
    @Transactional
    public void deleteUser(User user) {
        userRepository.delete(user);
    }

    @Override
    public User findUserById(long id) {
        Optional<User> returnedUser = userRepository.findById(id);
        if (!returnedUser.isPresent()){
            throw new IllegalArgumentException("id: " + id + " not issue");
        }
        return returnedUser.get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional
    public void deleteUserById(long id) {
        Optional<User> deletedUser = userRepository.findById(id);
        if(!deletedUser.isPresent()) {
            throw new IllegalArgumentException("id: " + id + " not issue");
        }
        userRepository.delete(deletedUser.get());
    }

    @Override
    @Transactional
    public Set<Role> getUserRole(Set<String> rolesId) {
        Set<Role> roles = new HashSet<>();
        for (String id: rolesId){
            roles.add(userRepository.getRoleById(Integer.parseInt(id)));
        }
        return roles;
    }
}
