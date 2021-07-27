package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.FuelTypeEntity;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelTypeEntity, Long> {
}