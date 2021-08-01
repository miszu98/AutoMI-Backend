package pl.malek.automi.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Car;
import pl.malek.automi.Entities.CarEntity;
import pl.malek.automi.Exceptions.*;
import pl.malek.automi.Mappers.*;
import pl.malek.automi.Repository.CarRepository;
import pl.malek.automi.Service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;
    private final ModelMapper modelMapper;
    private final FuelTypeMapper fuelTypeMapper;
    private final DrivingGearMapper drivingGearMapper;
    private final ColorMapper colorMapper;


    @Override
    public Car add(Car car, BindingResult result) {
        return null;
    }

    @Override
    public List<Car> getAll() {
        return null;
    }

    @Override
    public Car delete(long id) {
        return null;
    }

    @Override
    public Car update(long id, Car car, BindingResult result) throws CarNotFoundException, CarCreationException, MarkNotFoundException, ColorNotFoundException, FuelTypeNotFoundException, ModelNotFoundException, DrivingGearNotFoundException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var carEntity = carMapper.dtoToEntity(car);
        carRepository.save(carEntity);
        return carMapper.entityToDto(carEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws CarCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new CarCreationException(messages);
    }

    @Override
    public CarEntity getById(Long id) throws CarNotFoundException {
        return carRepository.findById(id).orElseThrow(
                () -> new CarNotFoundException(
                        String.format("Car with id: %s not found", id)
                )
        );
    }
}
