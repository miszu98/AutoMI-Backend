package pl.malek.automi.Mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.DTO.Car;
import pl.malek.automi.Entities.CarEntity;
import pl.malek.automi.Exceptions.*;
import pl.malek.automi.Service.*;

import java.time.LocalDate;

@Component
@AllArgsConstructor
public class CarMapper {

    private MarkService markService;
    private ModelService modelService;
    private FuelTypeService fuelTypeService;
    private DrivingGearService drivingGearService;
    private ColorService colorService;

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
                .yearOfProduction(LocalDate.parse(car.getYearOfProduction()))
                .build();
    }

}
