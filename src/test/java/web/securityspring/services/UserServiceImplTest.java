package web.securityspring.services;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;
import web.securityspring.models.Role;
import web.securityspring.models.User;
import web.securityspring.repository.RoleRepository;
import web.securityspring.repository.UserRepository;

import java.util.*;


@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
@Transactional
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;
    @Mock
    private UserRepository userRepository;
    @Mock
    private PasswordEncoder passwordEncoder;
    @Mock
    private RoleRepository roleRepository;

        @Test
        public void testAddUser() {
            User testUser = new User();
            testUser.setUsername("testUser");
            testUser.setPassword("password");
            Role testRole = new Role();
            testRole.setId(2L);
            Set<Role> roles = new HashSet<>();
            roles.add(testRole);

            Mockito.when(roleRepository.getRoleById(2)).thenReturn(testRole);
            Mockito.when(userRepository.saveAndFlush(Mockito.any(User.class))).thenReturn(testUser);

            User addedUser = userService.addUser(testUser);

            Mockito.verify(roleRepository, Mockito.times(1)).getRoleById(2);
            Mockito.verify(userRepository, Mockito.times(1)).saveAndFlush(testUser);
            Assert.assertEquals(testUser, addedUser);
        }



    @Test
    public void testFindUserById() {
        User testUser = new User();
        testUser.setId(1L);
        Optional<User> optionalUser = Optional.of(testUser);

        Mockito.when(userRepository.findById(1L)).thenReturn(optionalUser);

        Optional<User> foundUser = userService.findUserById(1);

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
        Assert.assertEquals(optionalUser, foundUser);
    }

    @Test
    public void testGetAllUser() {
        List<User> userList = new ArrayList<>();
        User user1 = new User();
        user1.setId(1L);
        user1.setUsername("user1");
        userList.add(user1);
        User user2 = new User();
        user2.setId(2L);
        user2.setUsername("user2");
        userList.add(user2);

        Mockito.when(userRepository.findAll()).thenReturn(userList);

        List<User> allUsers = userService.getAllUser();

        Mockito.verify(userRepository, Mockito.times(1)).findAll();
        Assert.assertEquals(userList, allUsers);
    }

    @Test
    public void testDeleteUserById() {
        userService.deleteUserById(1L);
        Mockito.verify(userRepository, Mockito.times(1)).deleteById(1L);
    }

    @Test
    public void testFindUserByName() {
        User testUser = new User();
        testUser.setUsername("testUser");
        Optional<User> optionalUser = Optional.of(testUser);

        Mockito.when(userRepository.findByUsername("testUser")).thenReturn(optionalUser);

        User foundUser = userService.findUserByName("testUser");

        Mockito.verify(userRepository, Mockito.times(1)).findByUsername("testUser");
        Assert.assertEquals(testUser, foundUser);
    }

    @Test
    public void testUpdateUser() {
        User originalUser = new User();
        originalUser.setId(1L);
        Optional<User> optionalUser = Optional.of(originalUser);

        User updatedUser = new User();
        updatedUser.setId(1L);
        updatedUser.setUsername("updatedUser");

        Mockito.when(userRepository.findById(1L)).thenReturn(optionalUser);
        Mockito.when(userRepository.saveAndFlush(Mockito.any(User.class))).thenReturn(updatedUser);

        User updated = userService.updateUser(updatedUser);

        Mockito.verify(userRepository, Mockito.times(1)).findById(1L);
        Mockito.verify(userRepository, Mockito.times(1)).saveAndFlush(updatedUser);
        Assert.assertEquals(updatedUser, updated);
    }
}