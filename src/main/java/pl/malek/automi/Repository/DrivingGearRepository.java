package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.DrivingGearEntity;

import java.util.Optional;

@Repository
public interface DrivingGearRepository extends JpaRepository<DrivingGearEntity, Long> {
    Optional<DrivingGearEntity> findDrivingGearByDrivingGear(String drivingGear);
}
