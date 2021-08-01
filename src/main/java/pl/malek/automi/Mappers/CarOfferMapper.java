package pl.malek.automi.Mappers;


import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.DTO.CarOffer;
import pl.malek.automi.Entities.CarOfferEntity;
import pl.malek.automi.Exceptions.CarNotFoundException;
import pl.malek.automi.Exceptions.UserNotFoundException;
import pl.malek.automi.Service.CarService;
import pl.malek.automi.Service.UserService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class CarOfferMapper {

    private final CarMapper carMapper;
    private final UserMapper userMapper;

    private final CarService carService;
    private final UserService userService;

    public CarOffer entityToDto(CarOfferEntity carOfferEntity) {
        return CarOffer
                .builder()
                .id(carOfferEntity.getId())
                .title(carOfferEntity.getTitle())
                .description(carOfferEntity.getDescription())
                .car(carMapper.entityToDto(carOfferEntity.getCarEntity()))
                .user(userMapper.entityToDto(carOfferEntity.getUserEntity()))
                .price(carOfferEntity.getPrice())
                .build();
    }

    public CarOfferEntity dtoToEntity(CarOffer carOffer) throws CarNotFoundException, UserNotFoundException {
        return CarOfferEntity
                .builder()
                .title(carOffer.getTitle())
                .description(carOffer.getDescription())
                .carEntity(carService.getById(carOffer.getCar().getId()))
                .userEntity(userService.getById(carOffer.getUser().getId()))
                .price(carOffer.getPrice())
                .build();
    }

    public List<CarOffer> entitiesToDto(List<CarOfferEntity> carOffers) {
        return carOffers
                .stream()
                .map(
                        carOfferEntity
                                -> CarOffer
                                    .builder()
                                    .id(carOfferEntity.getId())
                                    .title(carOfferEntity.getTitle())
                                    .description(carOfferEntity.getDescription())
                                    .car(carMapper.entityToDto(carOfferEntity.getCarEntity()))
                                    .user(userMapper.entityToDto(carOfferEntity.getUserEntity()))
                                    .price(carOfferEntity.getPrice())
                                    .build()
                ).collect(Collectors.toList());
    }


}
