package pl.malek.automi.Service;

import org.checkerframework.checker.nullness.Opt;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.DTO.Car;
import pl.malek.automi.Entities.CarEntity;
import pl.malek.automi.Exceptions.*;
import pl.malek.automi.Mappers.CarMapper;
import pl.malek.automi.Repository.CarRepository;
import pl.malek.automi.Service.Impl.CarServiceImpl;
import pl.malek.automi.UtilsMI.Constants;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class CarServiceTests {

    @Mock
    private CarRepository carRepository;

    @Mock
    private CarMapper carMapper;

    @InjectMocks
    private CarServiceImpl carService;

    @Captor
    private ArgumentCaptor<CarEntity> carCaptor;

    @Mock
    private BindingResult bindingResult;

    @Test
    void shouldReturnAllCars() {
        var cars = List.of(
                Constants.Cars.DataTransferObjects.BMW,
                Constants.Cars.DataTransferObjects.MERCEDES_BENZ
        );

        given(carService.getAll()).willReturn(cars);

        verify(carRepository).findAll();

        var response = carService.getAll();

        assertEquals(2, response.size());
        assertEquals(Constants.Marks.Labels.MERCEDES_BENZ, response.get(1).getMark().getMark());
        assertEquals(500L, response.get(1).getPower());
        assertNotEquals(Constants.Models.Labels.M5, response.get(1).getModel().getModel());
    }

    @Test
    void shouldAddCar() throws ColorNotFoundException, MarkNotFoundException, DrivingGearNotFoundException, ModelNotFoundException, FuelTypeNotFoundException, GearboxNotFoundException {
        var newCar = Constants.Cars.DataTransferObjects.MERCEDES_BENZ;
        var newCarEntity = Constants.Cars.Entities.MERCEDES_BENZ;

        given(carMapper.dtoToEntity(newCar)).willReturn(newCarEntity);

        carService.add(newCar);

        carCaptor = ArgumentCaptor.forClass(CarEntity.class);

        verify(carRepository).save(carCaptor.capture());

        var arg = carCaptor.getValue();

        assertThat(arg).isEqualTo(newCarEntity);
        assertEquals(Constants.Models.Labels.C63S, arg.getModel().getModel());

    }

    @Test
    void shouldDeleteCar() throws CarNotFoundException {
        var id = 2L;
        var foundedCar = Constants.Cars.Entities.MERCEDES_BENZ;

        given(carRepository.findById(id)).willReturn(Optional.of(foundedCar));

        carService.delete(id);

        verify(carRepository).deleteById(id);

        assertEquals(id, carService.getById(id).getId());
    }

    @Test
    void shouldUpdateCar() throws ColorNotFoundException, MarkNotFoundException, CarNotFoundException, CarCreationException, DrivingGearNotFoundException, ModelNotFoundException, FuelTypeNotFoundException, GearboxNotFoundException {
        var id = 2L;
        var updatedCarEntity = Constants.Cars.Entities.MERCEDES_BENZ_UPDATED;
        var updatedCarDTO = Constants.Cars.DataTransferObjects.MERCEDES_BENZ;
        updatedCarDTO.setColor(Constants.Colors.DataTransferObjects.RED);
        updatedCarDTO.setPower(700L);

        given(carRepository.existsById(id)).willReturn(true);
        given(carMapper.dtoToEntity(updatedCarDTO)).willReturn(updatedCarEntity);

        carService.update(id, updatedCarDTO, bindingResult);

        carCaptor = ArgumentCaptor.forClass(CarEntity.class);

        verify(carRepository).save(carCaptor.capture());

        assertThat(updatedCarEntity).isEqualTo(carCaptor.getValue());
        assertEquals(700L, carCaptor.getValue().getPower());

    }
}
