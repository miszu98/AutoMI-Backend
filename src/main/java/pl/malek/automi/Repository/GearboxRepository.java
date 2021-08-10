package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.GearboxEntity;

import java.util.Optional;

@Repository
public interface GearboxRepository extends JpaRepository<GearboxEntity, Long> {
    Optional<GearboxEntity> findDrivingGearEntityByDrivingGearName(String drivingGearName);

}
