package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.CarEntity;
import pl.malek.automi.Entities.ModelEntity;

import java.util.Optional;

@Repository
public interface CarRepository extends JpaRepository<CarEntity, Long> {
    Optional<CarEntity> findCarEntityByModel(ModelEntity modelEntity);
}
