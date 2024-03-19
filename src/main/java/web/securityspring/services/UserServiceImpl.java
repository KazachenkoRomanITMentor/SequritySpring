package web.securityspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import web.securityspring.models.User;
import web.securityspring.repository.RoleRepository;
import web.securityspring.repository.UserRepository;
import java.util.*;

import java.util.*;

@Service
public class UserServiceImpl implements UserService{
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final RoleRepository roleRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.roleRepository = roleRepository;
    }

    @Override
    @Transactional
    public User addUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRoles(new HashSet<>(Arrays.asList(roleRepository.getRoleById(2))));
        return userRepository.saveAndFlush(user);
    }

    @Override
    public Optional<User> findUserById(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<User> getAllUser() {
        return userRepository.findAll();
    }

    @Override
    @Transactional(Transactional.TxType.REQUIRES_NEW)
    public void deleteUserById(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User findUserByName(String name) {
        return userRepository.findByUsername(name).orElseGet(User::new);
    }

    @Override
    @Transactional
    public User updateUser(User user) {
        Optional<User> updatableUser = userRepository.findById(user.getId());
        if (updatableUser.isEmpty()) {
            throw new IllegalArgumentException("This user is not issue");
        }
      return   userRepository.saveAndFlush(user);
    }

}
