package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.CarOfferEntity;

@Repository
public interface CarOfferRepository extends JpaRepository<CarOfferEntity, Long> {
}
