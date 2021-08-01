package pl.malek.automi.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.CarOffer;
import pl.malek.automi.Entities.CarOfferEntity;
import pl.malek.automi.Exceptions.CarNotFoundException;
import pl.malek.automi.Exceptions.CarOfferCreationException;
import pl.malek.automi.Exceptions.CarOfferNotFoundException;
import pl.malek.automi.Exceptions.UserNotFoundException;

import java.util.List;

public interface CarOfferService {
    CarOffer add(CarOffer carOffer, BindingResult result) throws CarNotFoundException, UserNotFoundException, CarOfferCreationException;

    List<CarOffer> getAll();

    CarOffer delete(long id) throws CarOfferNotFoundException;

    CarOffer update(long id, CarOffer carOffer, BindingResult result) throws CarOfferNotFoundException, CarOfferCreationException, CarNotFoundException, UserNotFoundException;

    void extractErrors(List<ObjectError> errors) throws CarOfferCreationException;

    CarOfferEntity getById(Long id) throws CarOfferNotFoundException;
}
