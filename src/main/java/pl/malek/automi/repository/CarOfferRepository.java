package pl.malek.automi.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.CarOfferEntity;

@Repository
public interface CarOfferRepository extends PagingAndSortingRepository<CarOfferEntity, Long> {

}
