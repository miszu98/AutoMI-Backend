package pl.malek.automi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.CarOfferEntity;

@Repository
public interface CarOfferRepository extends JpaRepository<CarOfferEntity, Long> {
}
