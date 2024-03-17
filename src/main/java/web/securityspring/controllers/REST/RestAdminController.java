package web.securityspring.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import web.securityspring.mapper.UserReadMapper;
import web.securityspring.models.DTO.UserDTO;
import web.securityspring.models.User;
import web.securityspring.services.UserService;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/admin")
public class RestAdminController {
    private final UserService userService;
    private final UserReadMapper userReadMapper;



    @Autowired
    public RestAdminController(UserService userService, UserReadMapper userReadMapper) {
        this.userService = userService;
        this.userReadMapper = userReadMapper;
    }

    @GetMapping("/")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUser().stream()
                .map(userReadMapper::map)
                .toList();
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<User> getUserById(@PathVariable("id") long id) {
        ResponseEntity<User> response;
        Optional<User> user = userService.findUserById(id);
        response = user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
        return response;
    }


    @PostMapping(value = "/adduser")
    @ResponseStatus(HttpStatus.CREATED)
    public UserDTO create(@RequestBody User user){
        var returnedUser = userReadMapper.map( userService.addUser(user));
        return returnedUser;
    }

    @PatchMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable("id") long id,
                       @RequestBody User user){
      return   userService.updateUser(user);
    }
    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable long id) {
        userService.deleteUserById(id);
    }

}
