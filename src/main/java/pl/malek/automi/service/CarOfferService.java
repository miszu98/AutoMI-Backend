package pl.malek.automi.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.CarOffer;
import pl.malek.automi.entity.CarOfferEntity;
import pl.malek.automi.exception.*;

import java.util.List;

public interface CarOfferService {
    CarOffer add(CarOffer carOffer, BindingResult result) throws CarNotFoundException, UserNotFoundException, CarOfferCreationException, ColorNotFoundException, MarkNotFoundException, ModelNotFoundException, GearboxNotFoundException, FuelTypeNotFoundException, DrivingGearNotFoundException;

    List<CarOffer> getAll();

    CarOffer delete(long id) throws CarOfferNotFoundException;

    CarOffer update(long id, CarOffer carOffer, BindingResult result) throws CarOfferNotFoundException, CarOfferCreationException, CarNotFoundException, UserNotFoundException;

    void extractErrors(List<ObjectError> errors) throws CarOfferCreationException;

    CarOfferEntity getById(Long id) throws CarOfferNotFoundException;
}