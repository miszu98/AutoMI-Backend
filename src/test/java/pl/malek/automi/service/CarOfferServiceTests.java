package pl.malek.automi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.entity.CarOfferEntity;
import pl.malek.automi.exception.*;
import pl.malek.automi.mapper.CarOfferMapper;
import pl.malek.automi.repository.CarOfferRepository;
import pl.malek.automi.service.Impl.CarOfferServiceImpl;
import pl.malek.automi.service.Impl.CarServiceImpl;
import pl.malek.automi.utils.Constants;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class CarOfferServiceTests {

    @Mock
    private BindingResult bindingResult;

    @Mock
    private CarOfferRepository carOfferRepository;

    @Mock
    private CarServiceImpl carService;

    @Mock
    private CarOfferMapper carOfferMapper;

    @InjectMocks
    private CarOfferServiceImpl carOfferService;

    @Captor
    private ArgumentCaptor<CarOfferEntity> carOfferCaptor;

    @Test
    void shouldReturnAllOffers() {
        var carOffers = List.of(
                Constants.CarOffers.DataTransferObjects.CAR_OFFER
        );

        given(carOfferService.getAll()).willReturn(carOffers);

        verify(carOfferRepository).findAll();

        var response = carOfferService.getAll();

        assertEquals(1, response.size());
    }

    @Test
    void shouldAddCarOffer() throws UserNotFoundException, CarOfferCreationException, ColorNotFoundException, MarkNotFoundException, CarNotFoundException, DrivingGearNotFoundException, ModelNotFoundException, FuelTypeNotFoundException, GearboxNotFoundException {
        var newCarOffer = Constants.CarOffers.DataTransferObjects.CAR_OFFER;
        var newCarOfferEntity = Constants.CarOffers.Entities.CAR_OFFER;

        given(carOfferMapper.dtoToEntity(newCarOffer))
                .willReturn(newCarOfferEntity);

        carOfferService.add(newCarOffer, bindingResult);

        carOfferCaptor = ArgumentCaptor.forClass(CarOfferEntity.class);

        verify(carOfferRepository).save(carOfferCaptor.capture());

        var arg = carOfferCaptor.getValue();

        assertThat(newCarOfferEntity)
                .isEqualTo(arg);
        assertEquals(Constants.CarOffers.Titles.TITLE, arg.getTitle());
    }

    @Test
    void shouldDeleteCarOffer() throws CarOfferNotFoundException {
        var id = 1L;
        var foundedCarOffer = Constants.CarOffers.Entities.CAR_OFFER;

        given(carOfferRepository.findById(id))
                .willReturn(Optional.of(foundedCarOffer));

        carOfferService.delete(id);

        verify(carOfferRepository).deleteById(id);

        assertEquals(id, carOfferService.getById(id).getId());
    }

    @Test
    void shouldUpdateCarOffer() throws UserNotFoundException, CarOfferNotFoundException, CarOfferCreationException, CarNotFoundException {
        var id = 1L;
        var updatedCarOffer = Constants.CarOffers.DataTransferObjects.UPDATED_CAR_OFFER;
        var updatedCarOfferEntity = Constants.CarOffers.Entities.UPDATED_CAR_OFFER;

        given(carOfferMapper.dtoToEntity(updatedCarOffer)).willReturn(updatedCarOfferEntity);
        given(carOfferRepository.existsById(id)).willReturn(true);

        carOfferService.update(id, updatedCarOffer, bindingResult);

        carOfferCaptor = ArgumentCaptor.forClass(CarOfferEntity.class);

        verify(carOfferRepository).save(carOfferCaptor.capture());

        var arg = carOfferCaptor.getValue();

        assertThat(arg).isEqualTo(updatedCarOfferEntity);

    }
}
