package pl.malek.automi.service;

import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.CarOffer;
import pl.malek.automi.dto.FilteredPage;
import pl.malek.automi.entity.CarOfferEntity;
import pl.malek.automi.enums.CarType;
import pl.malek.automi.enums.State;
import pl.malek.automi.exception.*;

import java.util.List;
import java.util.Map;

public interface CarOfferService {
    CarOffer add(CarOffer carOffer, BindingResult result) throws CarNotFoundException, UserNotFoundException, CarOfferCreationException, ColorNotFoundException, MarkNotFoundException, ModelNotFoundException, GearboxNotFoundException, FuelTypeNotFoundException, DrivingGearNotFoundException;

//    List<CarOffer> getAll();
//
//    List<CarOffer> getAll(Pageable pageable);

    CarOffer delete(long id) throws CarOfferNotFoundException;

    CarOffer update(long id, CarOffer carOffer, BindingResult result) throws CarOfferNotFoundException, CarOfferCreationException, CarNotFoundException, UserNotFoundException, ColorNotFoundException, MarkNotFoundException, DrivingGearNotFoundException, ModelNotFoundException, FuelTypeNotFoundException, GearboxNotFoundException, CarCreationException;

    void extractErrors(List<ObjectError> errors) throws CarOfferCreationException;

    CarOfferEntity getById(Long id) throws CarOfferNotFoundException;

    CarOffer getOfferById(Long id) throws CarOfferNotFoundException;

    FilteredPage filter(Map<String, String> params, Pageable pageable);

    Long transformId(Object id);

    CarType transformCarType(Object id);

    State transformState(Object id);

    List<CarOffer> getOffersByUser(Long id);

    List<CarOffer> getNewestOffer();
}
