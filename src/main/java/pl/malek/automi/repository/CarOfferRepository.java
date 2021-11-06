package pl.malek.automi.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.CarOfferEntity;
import pl.malek.automi.enums.CarType;
import pl.malek.automi.enums.State;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CarOfferRepository extends PagingAndSortingRepository<CarOfferEntity, Long> {
//    @Query("SELECT c FROM CarOfferEntity c " +
//            "WHERE (:markId is null or c.carEntity.mark.id = :markId) " +
//            "and (:modelId is null or c.carEntity.model.id = :modelId) " +
//            "and (:fuelTypeId is null or c.carEntity.fuelType.id = :fuelTypeId) " +
//            "and (:gearboxId is null or c.carEntity.gearbox.id = :gearboxId) " +
//            "and (:drivingGearId is null or c.carEntity.drivingGear.id = :drivingGearId)")
//    @Query("SELECT c FROM CarOfferEntity c " +
//        "WHERE (:markId is null or c.carEntity.mark.id = :markId) " +
//        "and (:modelId is null or c.carEntity.model.id = :modelId) " +
//        "and (:fuelTypeId is null or c.carEntity.fuelType.id = :fuelTypeId) " +
//        "and (:gearboxId is null or c.carEntity.gearbox.id = :gearboxId) " +
//        "and (:drivingGearId is null or c.carEntity.drivingGear.id = :drivingGearId) " +
//            "and (:carType is null or c.carEntity.carType = :carType) " +
//            "and (:state is null or c.carEntity.state = :state)")
    @Query("SELECT c FROM CarOfferEntity c " +
        "WHERE (:markId is null or c.carEntity.mark.id = :markId) " +
        "and (:modelId is null or c.carEntity.model.id = :modelId) " +
        "and (:fuelTypeId is null or c.carEntity.fuelType.id = :fuelTypeId) " +
        "and (:gearboxId is null or c.carEntity.gearbox.id = :gearboxId) " +
        "and (:drivingGearId is null or c.carEntity.drivingGear.id = :drivingGearId) " +
        "and (:carType is null or c.carEntity.carType = :carType) " +
        "and (:state is null or c.carEntity.state = :state) " +
            "and (:city is null or c.city = :city) " +
            "and ((:powerStartRange is null or c.carEntity.power >= :powerStartRange) " +
            "and (:powerEndRange is null or c.carEntity.power <= :powerEndRange)) " +
            "and ((:ecStartRange is null or c.carEntity.engineCapacity >= :ecStartRange) " +
            "and (:ecEndRange is null or c.carEntity.engineCapacity <= :ecEndRange))")
    Page<CarOfferEntity> findCarOfferEntitiesByParams(
            @Param("markId") Long markId,
            @Param("modelId") Long modelId,
            @Param("fuelTypeId") Long fuelTypeId,
            @Param("gearboxId") Long gearboxId,
            @Param("drivingGearId") Long drivingGearId,
            @Param("carType") CarType carType,
            @Param("state") State state,
            @Param("city") String city,
            @Param("powerStartRange") Long startPower,
            @Param("powerEndRange") Long endPower,
            @Param("ecStartRange") Long ecStartRange,
            @Param("ecEndRange") Long ecEndRange,
            Pageable pageable
    );

    List<CarOfferEntity> findCarOfferEntitiesByUserEntityId(Long id);

    List<CarOfferEntity> findCarOfferEntitiesByCreatedAt(LocalDate date);


}
