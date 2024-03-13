package web.securityspring.mapper;

import org.springframework.stereotype.Component;
import web.securityspring.models.DTO.UserDTO;
import web.securityspring.models.User;



@Component
public class UserReadMapper implements  Mapper<User, UserDTO>{

    @Override
    public UserDTO map(User baseType) {
        return new UserDTO (
                baseType.getUsername(),
                baseType.getName(),
                baseType.getSurname(),
                baseType.getAge(),
                baseType.getRoles()
        );
    }
}
