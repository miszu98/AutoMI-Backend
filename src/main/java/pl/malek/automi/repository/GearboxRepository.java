package pl.malek.automi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.GearboxEntity;

import java.util.Optional;

@Repository
public interface GearboxRepository extends JpaRepository<GearboxEntity, Long> {
    Optional<GearboxEntity> findGearboxEntitiesByGearbox(String drivingGearName);

}
