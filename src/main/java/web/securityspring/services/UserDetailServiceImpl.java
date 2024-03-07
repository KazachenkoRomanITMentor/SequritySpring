package web.securityspring.services;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.securityspring.models.User;
import web.securityspring.repository.UserRepository;

import java.util.Optional;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    private final UserRepository userRepository;

    @Autowired
    public UserDetailServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> returnedUser = userRepository.findByUsername(username);
        if (returnedUser.isPresent()){
            return returnedUser.get();
        }else {
            throw new IllegalArgumentException("User with usernane: " + username + " is not issue");
        }
    }
}
