package pl.malek.automi.Repository;


import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.Entities.ModelEntity;


import java.util.Optional;

@Repository
public interface ModelRepository extends PagingAndSortingRepository<ModelEntity, Long> {
    Optional<ModelEntity> findByModel(String model);
}
