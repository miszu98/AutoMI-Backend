package pl.malek.automi.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.DTO.Mark;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.Entities.ModelEntity;

import java.util.List;
import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<MarkEntity, Long> {

    Optional<MarkEntity> findMarkEntityByMark(String mark);

}
