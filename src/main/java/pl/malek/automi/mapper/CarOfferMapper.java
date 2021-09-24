package pl.malek.automi.mapper;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;
import pl.malek.automi.dto.CarOffer;
import pl.malek.automi.dto.Image;
import pl.malek.automi.entity.CarOfferEntity;
import pl.malek.automi.entity.ImageEntity;
import pl.malek.automi.exception.CarNotFoundException;
import pl.malek.automi.exception.UserNotFoundException;
import pl.malek.automi.service.CarService;
import pl.malek.automi.service.UserService;

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
                .city(carOfferEntity.getCity())
                .createdAt(carOfferEntity.getCreatedAt())
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
                .city(carOffer.getCity())
                .createdAt(carOffer.getCreatedAt())
                .build();
    }

    public List<CarOffer> entitiesToDto(Page<CarOfferEntity> carOffers) {
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
                                    .city(carOfferEntity.getCity())
                                    .images(carOfferEntity.getImages().stream().map(image -> Image.builder().url(image.getUrl()).build()).collect(Collectors.toList()))
                                    .createdAt(carOfferEntity.getCreatedAt())
                                    .build()
                ).collect(Collectors.toList());
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
                                .city(carOfferEntity.getCity())
                                .images(carOfferEntity.getImages().stream().map(image -> Image.builder().url(image.getUrl()).build()).collect(Collectors.toList()))
                                .createdAt(carOfferEntity.getCreatedAt())
                                .build()
                ).collect(Collectors.toList());
    }


}
