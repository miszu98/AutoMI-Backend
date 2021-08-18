package pl.malek.automi.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.dto.Car;
import pl.malek.automi.entity.CarEntity;
import pl.malek.automi.exception.*;
import pl.malek.automi.service.*;
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
    private final DrivingGearService drivingGearService;


    private final GearboxMapper gearboxMapper;
    private final ColorMapper colorMapper;
    private final MarkMapper markMapper;
    private final ModelMapper modelMapper;
    private final FuelTypeMapper fuelTypeMapper;
    private final DrivingGearMapper drivingGearMapper;



    public CarEntity dtoToEntity(Car car) throws MarkNotFoundException, ModelNotFoundException, FuelTypeNotFoundException, GearboxNotFoundException, ColorNotFoundException, DrivingGearNotFoundException {
        return CarEntity
                .builder()
                .mark(markService.getById(car.getMark().getId()))
                .model(modelService.getById(car.getModel().getId()))
                .fuelType(fuelTypeService.getById(car.getFuelType().getId()))
                .power(car.getPower())
                .engineCapacity(car.getEngineCapacity())
                .gearbox(gearboxService.getById(car.getGearbox().getId()))
                .state(car.getState())
                .mileage(car.getMileage())
                .carType(car.getCarType())
                .drivingGear(drivingGearService.getById(car.getDrivingGear().getId()))
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
                .gearbox(gearboxMapper.entityToDto(carEntity.getGearbox()))
                .state(carEntity.getState())
                .mileage(carEntity.getMileage())
                .carType(carEntity.getCarType())
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
                                        .mark(markMapper.entityToDto(carEntity.getMark()))
                                        .model(modelMapper.entityToDto(carEntity.getModel()))
                                        .fuelType(fuelTypeMapper.entityToDto(carEntity.getFuelType()))
                                        .power(carEntity.getPower())
                                        .engineCapacity(carEntity.getEngineCapacity())
                                        .gearbox(gearboxMapper.entityToDto(carEntity.getGearbox()))
                                        .state(carEntity.getState())
                                        .mileage(carEntity.getMileage())
                                        .carType(carEntity.getCarType())
                                        .drivingGear(drivingGearMapper.entityToDto(carEntity.getDrivingGear()))
                                        .color(colorMapper.entityToDto(carEntity.getColor()))
                                        .yearOfProduction(carEntity.getYearOfProduction().toString())
                                        .build()
                ).collect(Collectors.toList());
    }

}
