package web.securityspring.mapper;

import org.springframework.stereotype.Component;
import web.securityspring.models.DTO.RoleDTO;
import web.securityspring.models.Role;
@Component
public class RoleReadMapper implements Mapper<Role, RoleDTO>{
    @Override
    public RoleDTO map(Role baseType) {
        return new RoleDTO(
                baseType.getId(),
                baseType.getRolename(),
                baseType.getUsers()
                );
    }
}
