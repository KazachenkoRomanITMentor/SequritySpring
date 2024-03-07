package web.securityspring.repository;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import web.securityspring.models.Role;
import web.securityspring.models.User;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    Role getRoleById(long id);

    @EntityGraph(attributePaths = "roles")
    Optional<User> findByUsername(String name);
}
