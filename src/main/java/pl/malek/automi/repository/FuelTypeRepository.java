package pl.malek.automi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.FuelTypeEntity;

import java.util.Optional;

@Repository
public interface FuelTypeRepository extends JpaRepository<FuelTypeEntity, Long> {
    Optional<FuelTypeEntity> findFuelTypeEntityByFuelTypeName(String fuelTypeName);
}
