package softuni.mobilelele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mobilelele.domain.entities.Brand;

@Repository
public interface BrandRepository extends JpaRepository<Brand, String> {
}
