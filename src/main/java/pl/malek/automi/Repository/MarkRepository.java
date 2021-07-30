package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.MarkEntity;

@Repository
public interface MarkRepository extends JpaRepository<MarkEntity, Long> {
}
