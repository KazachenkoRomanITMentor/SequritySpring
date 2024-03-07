package web.securityspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import web.securityspring.models.Role;
import web.securityspring.repository.RoleRepository;

import java.util.Set;

@Service
public class RoleServiceImpl implements RoleService{

    private  RoleRepository roleRepository;

    @Autowired
    public RoleServiceImpl(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Override
    public Role addNewRole(Role role) {
        return roleRepository.saveAndFlush(role);
    }

    @Override
    public void deleteRole(Role role) {
        roleRepository.delete(role);
    }

    @Override
    public Set<Role> getAllRoles() {
        return  Set.copyOf(roleRepository.findAll()) ;
    }
}
