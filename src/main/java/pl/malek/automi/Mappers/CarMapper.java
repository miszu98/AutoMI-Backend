package pl.malek.automi.Mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.DTO.Car;
import pl.malek.automi.Entities.CarEntity;
import pl.malek.automi.Exceptions.*;
import pl.malek.automi.Service.*;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CarMapper {

    private final MarkService markService;
    private final ModelService modelService;
    private final FuelTypeService fuelTypeService;
    private final GearboxService gearboxService;
    private final ColorService colorService;


    private final GearboxMapper gearboxMapper;
    private final ColorMapper colorMapper;
    private final MarkMapper markMapper;
    private final ModelMapper modelMapper;
    private final FuelTypeMapper fuelTypeMapper;


    public CarEntity dtoToEntity(Car car) throws MarkNotFoundException, ModelNotFoundException, FuelTypeNotFoundException, GearboxNotFoundException, ColorNotFoundException {
        return CarEntity
                .builder()
                .mark(markService.getById(car.getMark().getId()))
                .model(modelService.getById(car.getModel().getId()))
                .fuelType(fuelTypeService.getById(car.getFuelType().getId()))
                .power(car.getPower())
                .engineCapacity(car.getEngineCapacity())
                .drivingGear(gearboxService.getById(car.getGearbox().getId()))
                .color(colorService.getById(car.getColor().getId()))
                .build();
    }

    public Car entityToDto(CarEntity carEntity) {
        return Car
                .builder()
                .id(carEntity.getId())
                .mark(markMapper.entityToDto(carEntity.getMark()))
                .model(modelMapper.entityToDto(carEntity.getModel()))
                .fuelType(fuelTypeMapper.entityToDto(carEntity.getFuelType()))
                .power(carEntity.getPower())
                .engineCapacity(carEntity.getEngineCapacity())
                .gearbox(gearboxMapper.entityToDto(carEntity.getDrivingGear()))
                .color(colorMapper.entityToDto(carEntity.getColor()))
                .build();
    }

    public List<Car> entitiesToDto(List<CarEntity> cars) {
        return cars
                .stream()
                .map(
                        carEntity -> Car
                                        .builder()
                                        .id(carEntity.getId())
                                        .mark(markMapper.entityToDto(carEntity.getMark()))
                                        .model(modelMapper.entityToDto(carEntity.getModel()))
                                        .fuelType(fuelTypeMapper.entityToDto(carEntity.getFuelType()))
                                        .power(carEntity.getPower())
                                        .engineCapacity(carEntity.getEngineCapacity())
                                        .gearbox(gearboxMapper.entityToDto(carEntity.getDrivingGear()))
                                        .color(colorMapper.entityToDto(carEntity.getColor()))
                                        .yearOfProduction(carEntity.getYearOfProduction().toString())
                                        .build()
                ).collect(Collectors.toList());
    }

}
