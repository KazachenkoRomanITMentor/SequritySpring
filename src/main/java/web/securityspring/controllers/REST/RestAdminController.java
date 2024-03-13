package web.securityspring.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import web.securityspring.mapper.UserReadMapper;
import web.securityspring.models.DTO.UserDTO;
import web.securityspring.models.User;
import web.securityspring.services.RoleService;
import web.securityspring.services.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
public class RestAdminController {
    private final UserService userService;
    private final UserReadMapper userReadMapper;
    private final RoleService roleService;

    @Autowired
    public RestAdminController(UserService userService, UserReadMapper userReadMapper, RoleService roleService) {
        this.userService = userService;
        this.userReadMapper = userReadMapper;
        this.roleService = roleService;
    }

    @GetMapping("/")
    public List<UserDTO> getAllUsers(){
        return userService.getAllUser().stream()
                .map(userReadMapper::map)
                .toList();
    }

    @GetMapping("user/{id}")
    public User getUserById(@PathVariable("id") long id){
        return userService.findUserById(id);
    }


    @PostMapping(value = "/adduser")
    @ResponseStatus(HttpStatus.CREATED)
    public User create(@RequestBody User user){
        return userService.addUser(user);
    }

    @PatchMapping("/edit/{id}")
    @ResponseStatus(HttpStatus.OK)
    public User update(@PathVariable("id") long id,
                       @RequestBody User user){
      return   userService.updateUser(user);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete (@PathVariable long id){
        userService.deleteUserById(id);
    }

}
