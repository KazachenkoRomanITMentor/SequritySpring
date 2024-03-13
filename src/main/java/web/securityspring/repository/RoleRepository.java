package web.securityspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import web.securityspring.models.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {
    Role getRoleById(long id);
}
