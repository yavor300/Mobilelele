package softuni.mobilelele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mobilelele.domain.entities.Model;

@Repository
public interface ModelRepository extends JpaRepository<Model, String> {
}
