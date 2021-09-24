package pl.malek.automi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import pl.malek.automi.entity.ImageEntity;
import java.util.List;

@Repository
public interface ImageRepository extends JpaRepository<ImageEntity, Long> {
    @Modifying
    @Query("delete from ImageEntity img where img.carOfferEntity.id = :offerId")
    void deleteImagesByOfferId(@Param("offerId") Long offerId);
}
