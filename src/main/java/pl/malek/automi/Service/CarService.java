package pl.malek.automi.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Car;
import pl.malek.automi.Entities.CarEntity;
import pl.malek.automi.Exceptions.*;

import java.util.List;

public interface CarService {
    Car add(Car car, BindingResult result) throws ColorNotFoundException, MarkNotFoundException, ModelNotFoundException, GearboxNotFoundException, FuelTypeNotFoundException, CarCreationException;

    Car add(Car car) throws ColorNotFoundException, MarkNotFoundException, ModelNotFoundException, GearboxNotFoundException, FuelTypeNotFoundException;

    List<Car> getAll();

    Car delete(long id) throws CarNotFoundException;

    Car update(long id, Car car, BindingResult result) throws CarNotFoundException, CarCreationException, MarkNotFoundException, ColorNotFoundException, FuelTypeNotFoundException, ModelNotFoundException, GearboxNotFoundException;

    void extractErrors(List<ObjectError> errors) throws CarCreationException;

    CarEntity getById(Long id) throws CarNotFoundException;
}
