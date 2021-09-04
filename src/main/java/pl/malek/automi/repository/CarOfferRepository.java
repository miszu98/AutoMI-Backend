package pl.malek.automi.repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.CarOfferEntity;
import java.util.List;

@Repository
public interface CarOfferRepository extends PagingAndSortingRepository<CarOfferEntity, Long> {
    @Query("SELECT c FROM CarOfferEntity c " +
            "WHERE (:markId is null or c.carEntity.mark.id = :markId) " +
            "and (:modelId is null or c.carEntity.model.id = :modelId) " +
            "and (:fuelTypeId is null or c.carEntity.fuelType.id = :fuelTypeId) " +
            "and (:gearboxId is null or c.carEntity.gearbox.id = :gearboxId) " +
            "and (:drivingGearId is null or c.carEntity.drivingGear.id = :drivingGearId)")
    List<CarOfferEntity> findCarOfferEntitiesByParams(
            @Param("markId") Long markId,
            @Param("modelId") Long modelId,
            @Param("fuelTypeId") Long fuelTypeId,
            @Param("gearboxId") Long gearboxId,
            @Param("drivingGearId") Long drivingGearId
    );
}
