package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.ColorEntity;
import pl.malek.automi.Entities.FuelTypeEntity;

import java.util.Optional;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Long> {
    Optional<ColorEntity> findColorEntityByColorName(String colorName);

}
