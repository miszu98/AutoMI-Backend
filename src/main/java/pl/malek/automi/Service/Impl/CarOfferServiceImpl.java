package pl.malek.automi.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.CarOffer;
import pl.malek.automi.Entities.CarOfferEntity;
import pl.malek.automi.Exceptions.*;
import pl.malek.automi.Mappers.CarOfferMapper;
import pl.malek.automi.Repository.CarOfferRepository;
import pl.malek.automi.Service.CarOfferService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class CarOfferServiceImpl implements CarOfferService {

    private final CarOfferRepository carOfferRepository;
    private final CarOfferMapper carOfferMapper;

    @Override
    public CarOffer add(CarOffer carOffer, BindingResult result) throws CarNotFoundException, UserNotFoundException, CarOfferCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
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
