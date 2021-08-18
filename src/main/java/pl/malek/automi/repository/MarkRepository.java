package pl.malek.automi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.MarkEntity;

import java.util.Optional;

@Repository
public interface MarkRepository extends JpaRepository<MarkEntity, Long> {

    Optional<MarkEntity> findMarkEntityByMark(String mark);

}
