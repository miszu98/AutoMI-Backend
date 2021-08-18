package pl.malek.automi.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.Car;
import pl.malek.automi.entity.CarEntity;
import pl.malek.automi.exception.*;

import java.util.List;

public interface CarService {
    Car add(Car car, BindingResult result) throws ColorNotFoundException, MarkNotFoundException, ModelNotFoundException, GearboxNotFoundException, FuelTypeNotFoundException, CarCreationException, DrivingGearNotFoundException;

    Car add(Car car) throws ColorNotFoundException, MarkNotFoundException, ModelNotFoundException, GearboxNotFoundException, FuelTypeNotFoundException, DrivingGearNotFoundException;

    List<Car> getAll();

    Car delete(long id) throws CarNotFoundException;

    Car update(long id, Car car, BindingResult result) throws CarNotFoundException, CarCreationException, MarkNotFoundException, ColorNotFoundException, FuelTypeNotFoundException, ModelNotFoundException, GearboxNotFoundException, DrivingGearNotFoundException;

    void extractErrors(List<ObjectError> errors) throws CarCreationException;

    CarEntity getById(Long id) throws CarNotFoundException;
}
