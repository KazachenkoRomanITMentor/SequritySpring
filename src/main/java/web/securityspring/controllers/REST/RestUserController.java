package web.securityspring.controllers.REST;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import web.securityspring.mapper.Mapper;
import web.securityspring.mapper.UserReadMapper;
import web.securityspring.models.DTO.UserDTO;
import web.securityspring.models.User;
import web.securityspring.services.UserService;

import java.net.http.HttpResponse;

@RestController
@RequestMapping("/api/v1/user")
public class RestUserController {
    private final UserService userService;
    private final UserReadMapper userReadMapper;

    @Autowired
    public RestUserController(UserService userService, UserReadMapper userReadMapper) {
        this.userService = userService;
        this.userReadMapper = userReadMapper;
    }

    @GetMapping("/")
    public ResponseEntity<UserDTO> showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        User user = userService.findUserByName(authentication.getName());
        UserDTO userDTO = userReadMapper.map(user);
        return  ResponseEntity.ok().body(userDTO);
    }

}
