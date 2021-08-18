package pl.malek.automi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.DrivingGearEntity;

import java.util.Optional;

@Repository
public interface DrivingGearRepository extends JpaRepository<DrivingGearEntity, Long> {
    Optional<DrivingGearEntity> findDrivingGearByDrivingGear(String drivingGear);
}
