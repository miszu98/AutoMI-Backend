package pl.malek.automi.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.FuelType;
import pl.malek.automi.entity.FuelTypeEntity;
import pl.malek.automi.exception.FuelTypeCreationException;
import pl.malek.automi.exception.FuelTypeNotFoundException;
import java.util.List;

public interface FuelTypeService {
    FuelType add(FuelType fuelType, BindingResult result) throws FuelTypeCreationException;

    List<FuelType> getAll();

    FuelType delete(long id) throws FuelTypeNotFoundException;

    FuelType update(long id, FuelType fuelType, BindingResult result) throws FuelTypeCreationException, FuelTypeNotFoundException;

    void extractErrors(List<ObjectError> errors) throws FuelTypeCreationException;

    FuelTypeEntity getById(Long id) throws FuelTypeNotFoundException;

}
