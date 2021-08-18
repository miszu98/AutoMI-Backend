package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.Car;
import pl.malek.automi.entity.CarEntity;
import pl.malek.automi.exception.*;
import pl.malek.automi.mapper.*;
import pl.malek.automi.repository.CarRepository;
import pl.malek.automi.service.CarService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;
    private final CarMapper carMapper;

    @Override
    public Car add(Car car, BindingResult result) throws ColorNotFoundException, MarkNotFoundException, ModelNotFoundException, GearboxNotFoundException, FuelTypeNotFoundException, CarCreationException, DrivingGearNotFoundException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var carEntity = carRepository.save(carMapper.dtoToEntity(car));
        return carMapper.entityToDto(carEntity);
    }

    @Override
    public Car add(Car car) throws ColorNotFoundException, MarkNotFoundException, ModelNotFoundException, GearboxNotFoundException, FuelTypeNotFoundException, DrivingGearNotFoundException {
        var carEntity = carRepository.save(carMapper.dtoToEntity(car));
        return carMapper.entityToDto(carEntity);
    }

    @Override
    public List<Car> getAll() {
        return carMapper.entitiesToDto(carRepository.findAll());
    }

    @Override
    public Car delete(long id) throws CarNotFoundException {
        var carEntity = getById(id);
        carRepository.deleteById(id);
        return carMapper.entityToDto(carEntity);
    }

    @Override
    public Car update(long id, Car car, BindingResult result) throws CarNotFoundException, CarCreationException, MarkNotFoundException, ColorNotFoundException, FuelTypeNotFoundException, ModelNotFoundException, GearboxNotFoundException, DrivingGearNotFoundException {
        if (!carRepository.existsById(id)) {
            throw new CarNotFoundException(String.format("Car with id: %d not found", id));
        }
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
                        String.format("Car with id: %d not found", id)
                )
        );
    }
}
