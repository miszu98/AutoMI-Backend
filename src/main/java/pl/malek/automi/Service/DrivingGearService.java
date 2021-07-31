package pl.malek.automi.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.DrivingGear;
import pl.malek.automi.Entities.DrivingGearEntity;
import pl.malek.automi.Exceptions.DrivinGearCreationException;
import pl.malek.automi.Exceptions.DrivingGearNotFoundException;

import java.util.List;

public interface DrivingGearService {
    DrivingGear add(DrivingGear drivingGear, BindingResult result) throws DrivinGearCreationException;

    List<DrivingGear> getAll();

    DrivingGear delete(long id) throws DrivingGearNotFoundException;

    DrivingGear update(long id, DrivingGear drivingGear, BindingResult result) throws DrivinGearCreationException, DrivingGearNotFoundException;

    void extractErrors(List<ObjectError> errors) throws DrivinGearCreationException;

    DrivingGearEntity getById(Long id) throws DrivingGearNotFoundException;
}
