package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.CarOffer;
import pl.malek.automi.dto.Image;
import pl.malek.automi.entity.CarOfferEntity;
import pl.malek.automi.entity.ImageEntity;
import pl.malek.automi.enums.CarType;
import pl.malek.automi.enums.State;
import pl.malek.automi.exception.*;
import pl.malek.automi.mapper.CarMapper;
import pl.malek.automi.mapper.CarOfferMapper;
import pl.malek.automi.repository.CarOfferRepository;
import pl.malek.automi.repository.ImageRepository;
import pl.malek.automi.service.CarOfferService;
import pl.malek.automi.service.CarService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CarOfferServiceImpl implements CarOfferService {

    private final CarOfferRepository carOfferRepository;
    private final CarOfferMapper carOfferMapper;
    private final CarMapper carMapper;
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
    public List<CarOffer> getAll(Pageable pageable) {
        return carOfferMapper.entitiesToDto(carOfferRepository.findAll(pageable));
    }

    @Override
    public List<CarOffer> getAll() {
        return carOfferMapper.entitiesToDto((Page<CarOfferEntity>) carOfferRepository.findAll());
    }

    @Override
    public CarOffer delete(long id) throws CarOfferNotFoundException {
        var carEntity = getById(id);
        carOfferRepository.deleteById(id);
        return carOfferMapper.entityToDto(carEntity);
    }

    @Override
    public CarOffer update(long id, CarOffer carOffer, BindingResult result) throws CarOfferNotFoundException, CarOfferCreationException, CarNotFoundException, UserNotFoundException, ColorNotFoundException, MarkNotFoundException, DrivingGearNotFoundException, ModelNotFoundException, FuelTypeNotFoundException, GearboxNotFoundException, CarCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var foundedCarOfferEntity = carOfferRepository.findById(id).orElseThrow(
                () -> new CarOfferNotFoundException(String.format("Car offer with id: %d not found", id))
        );

        var updatedCar = carService.update(carOffer.getCar().getId(), carOffer.getCar(), result);

        foundedCarOfferEntity.setTitle(carOffer.getTitle());
        foundedCarOfferEntity.setDescription(carOffer.getDescription());
        foundedCarOfferEntity.setCity(carOffer.getCity());
//        foundedCarOfferEntity.setImages(
//                carOffer.getImages().stream()
//                        .map(image -> ImageEntity.builder().url(image.getUrl()).build()).collect(Collectors.toList()));
        foundedCarOfferEntity.setPrice(carOffer.getPrice());
        foundedCarOfferEntity.setCarEntity(carMapper.dtoToEntity(updatedCar));

        carOfferRepository.save(foundedCarOfferEntity);


        return carOfferMapper.entityToDto(foundedCarOfferEntity);
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

    @Override
    public CarOffer getOfferById(Long id) throws CarOfferNotFoundException {
        return carOfferMapper.entityToDto(carOfferRepository.findById(id).orElseThrow(
                () -> new CarOfferNotFoundException(
                        String.format("Car offer with id: %d not found", id)
                )
        ));
    }

    @Override
    public List<CarOffer> getOffersByUser(Long id) {
        return carOfferMapper
                .entitiesToDto(carOfferRepository.findCarOfferEntitiesByUserEntityId(id));
    }

    @Override
    public List<CarOffer> filter(Map<String, Object> params, Pageable pageable) {
        return carOfferMapper.entitiesToDto(carOfferRepository
                .findCarOfferEntitiesByParams(
                        transformId(params.get("mark")),
                        transformId(params.get("model")),
                        transformId(params.get("fuelType")),
                        transformId(params.get("gearbox")),
                        transformId(params.get("drivingGear")),
                        transformCarType(params.get("carType")),
                        transformState(params.get("state")),
                        pageable
                ));
    }

    @Override
    public Long transformId(Object id) {
        if (id == null) {
            return null;
        } else {
            return Long.valueOf(String.valueOf(id));
        }
    }

    @Override
    public CarType transformCarType(Object id) {
        if (id == null) {
            return null;
        } else {
            CarType carType;
            try {
                carType = CarType.values()[Integer.parseInt(String.valueOf(id))];
                return carType;
            } catch (Exception e) {
                carType = null;
            }
            return carType;
        }
    }

    @Override
    public State transformState(Object id) {
        if (id == null) {
            return null;
        } else {
            State state;
            try {
                state = State.values()[Integer.parseInt(String.valueOf(id))];
                return state;
            } catch (Exception e) {
                state = null;
            }
            return state;
        }
    }
}
