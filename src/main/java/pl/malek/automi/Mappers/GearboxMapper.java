package pl.malek.automi.Mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.DTO.Gearbox;
import pl.malek.automi.Entities.GearboxEntity;

import java.util.List;
import java.util.stream.Collectors;


@Component
@AllArgsConstructor
public class GearboxMapper {

    public Gearbox entityToDto(GearboxEntity gearboxEntity) {
        return Gearbox
                .builder()
                .id(gearboxEntity.getId())
                .drivingGear(gearboxEntity.getGearbox())
                .build();
    }

    public GearboxEntity dtoToEntity(Gearbox gearbox) {
        return GearboxEntity
                .builder()
                .gearbox(gearbox.getDrivingGear())
                .build();
    }

    public List<Gearbox> entitiesToDto(List<GearboxEntity> drivingGearEntities) {
        return drivingGearEntities
                .stream()
                .map(
                        drivingGearEntity -> Gearbox
                                .builder()
                                .id(drivingGearEntity.getId())
                                .drivingGear(drivingGearEntity.getGearbox())
                                .build()
                ).collect(Collectors.toList());
    }

}
