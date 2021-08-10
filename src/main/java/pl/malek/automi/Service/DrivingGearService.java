package pl.malek.automi.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.DrivingGear;
import pl.malek.automi.Entities.DrivingGearEntity;
import pl.malek.automi.Exceptions.DrivingGearCreationException;
import pl.malek.automi.Exceptions.DrivingGearNotFoundException;

import java.util.List;

public interface DrivingGearService {
    DrivingGear add(DrivingGear drivingGear, BindingResult result) throws DrivingGearCreationException;

    List<DrivingGear> getAll();

    DrivingGear delete(long id) throws DrivingGearNotFoundException;

    DrivingGear update(long id, DrivingGear drivingGear, BindingResult result) throws DrivingGearCreationException, DrivingGearNotFoundException;

    void extractErrors(List<ObjectError> errors) throws DrivingGearCreationException;

    DrivingGearEntity getById(Long id) throws DrivingGearNotFoundException;
}
