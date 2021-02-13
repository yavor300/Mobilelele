package softuni.mobilelele.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import softuni.mobilelele.domain.entities.Offer;

@Repository
public interface OfferRepository extends JpaRepository<Offer, String> {
}
