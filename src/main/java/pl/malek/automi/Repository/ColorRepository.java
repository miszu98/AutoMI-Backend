package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.ColorEntity;

@Repository
public interface ColorRepository extends JpaRepository<ColorEntity, Long> {
}