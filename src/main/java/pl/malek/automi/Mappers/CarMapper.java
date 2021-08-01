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

    private MarkService markService;
    private ModelService modelService;
    private FuelTypeService fuelTypeService;
    private DrivingGearService drivingGearService;
    private ColorService colorService;


    private DrivingGearMapper drivingGearMapper;
    private ColorMapper colorMapper;


    public CarEntity dtoToEntity(Car car) throws MarkNotFoundException, ModelNotFoundException, FuelTypeNotFoundException, DrivingGearNotFoundException, ColorNotFoundException {
        return CarEntity
                .builder()
                .mark(markService.getById(car.getMark().getId()))
                .model(modelService.getById(car.getModel().getId()))
                .fuelType(fuelTypeService.getById(car.getFuelType().getId()))
                .power(car.getPower())
                .engineCapacity(car.getEngineCapacity())
                .drivingGear(drivingGearService.getById(car.getDrivingGear().getId()))
                .color(colorService.getById(car.getColor().getId()))
                .build();
    }

    public Car entityToDto(CarEntity carEntity) {
        return Car
                .builder()
                .id(carEntity.getId())
                .mark(MarkMapper.entityToDto(carEntity.getMark()))
                .model(ModelMapper.entityToDto(carEntity.getModel()))
                .fuelType(FuelTypeMapper.entityToDto(carEntity.getFuelType()))
                .power(carEntity.getPower())
                .engineCapacity(carEntity.getEngineCapacity())
                .drivingGear(drivingGearMapper.entityToDto(carEntity.getDrivingGear()))
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
                                        .mark(MarkMapper.entityToDto(carEntity.getMark()))
                                        .model(ModelMapper.entityToDto(carEntity.getModel()))
                                        .fuelType(FuelTypeMapper.entityToDto(carEntity.getFuelType()))
                                        .power(carEntity.getPower())
                                        .engineCapacity(carEntity.getEngineCapacity())
                                        .drivingGear(drivingGearMapper.entityToDto(carEntity.getDrivingGear()))
                                        .color(colorMapper.entityToDto(carEntity.getColor()))
                                        .yearOfProduction(carEntity.getYearOfProduction().toString())
                                        .build()
                ).collect(Collectors.toList());
    }

}
