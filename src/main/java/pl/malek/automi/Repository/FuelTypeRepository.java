package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.FuelTypeEntity;

import java.util.Optional;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelTypeEntity, Long> {
    Optional<FuelTypeEntity> findFuelTypeEntityByFuelTypeName(String fuelTypeName);
}
