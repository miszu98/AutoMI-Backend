package pl.malek.automi.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.dto.DrivingGear;
import pl.malek.automi.entity.DrivingGearEntity;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class DrivingGearMapper {

    public DrivingGear entityToDto(DrivingGearEntity drivingGearEntity) {
        return DrivingGear
                .builder()
                .id(drivingGearEntity.getId())
                .drivingGear(drivingGearEntity.getDrivingGear())
                .build();
    }

    public DrivingGearEntity dtoToEntity(DrivingGear drivingGear) {
        return DrivingGearEntity
                .builder()
                .drivingGear(drivingGear.getDrivingGear())
                .build();
    }

    public List<DrivingGear> entitiesToDto(List<DrivingGearEntity> drivingGearEntities) {
        return drivingGearEntities
                .stream()
                .map(
                        drivingGearEntity -> DrivingGear
                                .builder()
                                .id(drivingGearEntity.getId())
                                .drivingGear(drivingGearEntity.getDrivingGear())
                                .build()
                ).collect(Collectors.toList());
    }
}
