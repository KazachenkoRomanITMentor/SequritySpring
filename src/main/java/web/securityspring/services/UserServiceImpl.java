package web.securityspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import web.securityspring.models.Role;
import web.securityspring.models.User;
import web.securityspring.repository.RoleRepository;
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
    public UserServiceImpl(UserRepository userRepository, PasswordEncoder passwordEncoder, RoleRepository roleRepository) {
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

}
