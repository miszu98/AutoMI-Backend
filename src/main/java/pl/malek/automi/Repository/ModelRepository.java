package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.ModelEntity;

@Repository
public interface ModelRepository extends JpaRepository<ModelEntity, Long> {
}
