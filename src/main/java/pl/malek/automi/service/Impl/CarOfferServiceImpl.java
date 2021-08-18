package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.CarOffer;
import pl.malek.automi.entity.CarOfferEntity;
import pl.malek.automi.exception.*;
import pl.malek.automi.mapper.CarOfferMapper;
import pl.malek.automi.repository.CarOfferRepository;
import pl.malek.automi.service.CarOfferService;
import pl.malek.automi.service.CarService;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarOfferServiceImpl implements CarOfferService {

    private final CarOfferRepository carOfferRepository;
    private final CarOfferMapper carOfferMapper;
    private final CarService carService;

    @Override
    public CarOffer add(CarOffer carOffer, BindingResult result) throws CarNotFoundException, UserNotFoundException, CarOfferCreationException, ColorNotFoundException, MarkNotFoundException, ModelNotFoundException, GearboxNotFoundException, FuelTypeNotFoundException, DrivingGearNotFoundException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var car = carService.add(carOffer.getCar());
        carOffer.setCar(car);
        var carOfferEntity = carOfferRepository.save(carOfferMapper.dtoToEntity(carOffer));
        return carOfferMapper.entityToDto(carOfferEntity);
    }

    @Override
    public List<CarOffer> getAll() {
        return carOfferMapper.entitiesToDto(carOfferRepository.findAll());
    }

    @Override
    public CarOffer delete(long id) throws CarOfferNotFoundException {
        var carEntity = getById(id);
        carOfferRepository.deleteById(id);
        return carOfferMapper.entityToDto(carEntity);
    }

    @Override
    public CarOffer update(long id, CarOffer carOffer, BindingResult result) throws CarOfferNotFoundException, CarOfferCreationException, CarNotFoundException, UserNotFoundException {
        if (!carOfferRepository.existsById(id)) {
            throw new CarOfferNotFoundException(String.format("Car offer with id: %d not found", id));
        }
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var carOfferEntity = carOfferMapper.dtoToEntity(carOffer);
        carOfferRepository.save(carOfferEntity);
        return carOfferMapper.entityToDto(carOfferEntity);
    }


    @Override
    public void extractErrors(List<ObjectError> errors) throws CarOfferCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new CarOfferCreationException(messages);
    }

    @Override
    public CarOfferEntity getById(Long id) throws CarOfferNotFoundException {
        return carOfferRepository.findById(id).orElseThrow(
                () -> new CarOfferNotFoundException(
                        String.format("Car offer with id: %d not found", id)
                )
        );
    }
}
