package web.securityspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import web.securityspring.models.Role;
import web.securityspring.models.User;
import web.securityspring.repository.RoleRepository;
import web.securityspring.repository.UserRepository;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository, RoleRepository roleRepository1) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository1;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getRoleById(1))));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public User findUserById(long id) {
        Optional<User> returnedUser = userRepository.findById(id);
        if (returnedUser.isEmpty()){
            throw new IllegalArgumentException("id: " + id + " not issue");
        }
        return returnedUser.get();
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteUserById(long id) {
        Optional<User> deletedUser = userRepository.findById(id);
        if(deletedUser.isEmpty()) {
            throw new IllegalArgumentException("id: " + id + " not issue");
        }
        userRepository.delete(deletedUser.get());
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByUsername(name).get();
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        Optional<User> updatebleUser = userRepository.findById(user.getId());
        if (updatebleUser.isEmpty()) {
            throw new IllegalArgumentException("This user is not issue");
        }
      return   userRepository.saveAndFlush(user);
    }

}
