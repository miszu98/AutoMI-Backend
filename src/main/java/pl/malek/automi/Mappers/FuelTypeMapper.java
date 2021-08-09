package pl.malek.automi.Mappers;

import org.springframework.stereotype.Component;
import pl.malek.automi.DTO.FuelType;
import pl.malek.automi.Entities.FuelTypeEntity;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class FuelTypeMapper {

    public FuelTypeEntity dtoToEntity(FuelType fuelType) {
        return FuelTypeEntity
                .builder()
                .fuelTypeName(fuelType.getFuelTypeName())
                .build();
    }

    public List<FuelType> entitiesToDto(Iterable<FuelTypeEntity> fuelTypeEntities) {
        var list = new ArrayList<FuelTypeEntity>();
        fuelTypeEntities.forEach(list::add);
        return list
                .stream()
                .map(fuelType -> FuelType
                        .builder()
                        .id(fuelType.getId())
                        .fuelTypeName(fuelType.getFuelTypeName()).build()
                ).collect(Collectors.toList());
    }

    public FuelType entityToDto(FuelTypeEntity fuelTypeEntity) {
        return FuelType
                .builder()
                .id(fuelTypeEntity.getId())
                .fuelTypeName(fuelTypeEntity.getFuelTypeName())
                .build();
    }
}
