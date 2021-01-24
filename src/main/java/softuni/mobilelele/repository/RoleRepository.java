package softuni.mobilelele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mobilelele.domain.entities.Role;

@Repository
public interface RoleRepository extends JpaRepository<Role, String> {
    Role findByAuthority(String authority);
}
