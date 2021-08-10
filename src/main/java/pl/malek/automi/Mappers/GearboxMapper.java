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
                .gearbox(gearboxEntity.getGearbox())
                .build();
    }

    public GearboxEntity dtoToEntity(Gearbox gearbox) {
        return GearboxEntity
                .builder()
                .gearbox(gearbox.getGearbox())
                .build();
    }

    public List<Gearbox> entitiesToDto(List<GearboxEntity> gearboxEntities) {
        return gearboxEntities
                .stream()
                .map(
                        gearboxEntity -> Gearbox
                                .builder()
                                .id(gearboxEntity.getId())
                                .gearbox(gearboxEntity.getGearbox())
                                .build()
                ).collect(Collectors.toList());
    }

}
