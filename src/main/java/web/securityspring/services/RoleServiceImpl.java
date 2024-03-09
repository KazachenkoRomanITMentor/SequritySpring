package web.securityspring.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
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
    @Transactional
    public Set<Role> getAllRoles() {
        return  Set.copyOf(roleRepository.findAll()) ;
    }
}
