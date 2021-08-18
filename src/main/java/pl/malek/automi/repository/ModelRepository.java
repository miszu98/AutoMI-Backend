package pl.malek.automi.repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.ModelEntity;


import java.util.Optional;

@Repository
public interface ModelRepository extends PagingAndSortingRepository<ModelEntity, Long> {
    Optional<ModelEntity> findByModel(String model);
}
