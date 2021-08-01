package pl.malek.automi.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Car;
import pl.malek.automi.Entities.CarEntity;
import pl.malek.automi.Exceptions.*;

import java.util.List;

public interface CarService {
    Car add(Car car, BindingResult result);

    List<Car> getAll();

    Car delete(long id) throws ColorNotFoundException;

    Car update(long id, Car car, BindingResult result) throws CarNotFoundException, CarCreationException, MarkNotFoundException, ColorNotFoundException, FuelTypeNotFoundException, ModelNotFoundException, DrivingGearNotFoundException;

    void extractErrors(List<ObjectError> errors) throws CarCreationException;

    CarEntity getById(Long id) throws CarNotFoundException;
}
