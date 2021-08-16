package pl.malek.automi.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.UtilsMI.Constants;
import pl.malek.automi.DTO.FuelType;
import pl.malek.automi.Entities.FuelTypeEntity;
import pl.malek.automi.Exceptions.FuelTypeCreationException;
import pl.malek.automi.Exceptions.FuelTypeNotFoundException;
import pl.malek.automi.Mappers.FuelTypeMapper;
import pl.malek.automi.Repository.FuelTypeRepository;
import pl.malek.automi.Service.Impl.FuelTypeServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FuelTypeServiceTests {

    @Mock
    private FuelTypeRepository fuelTypeRepository;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private FuelTypeMapper fuelTypeMapper;

    @InjectMocks
    private FuelTypeServiceImpl fuelTypeService;

    @Captor
    private ArgumentCaptor<FuelTypeEntity> fuelTypeCaptor;

    @Test
    void shouldReturnAllFuelTypes() {
        var fuelTypes = List.of(
                Constants.FuelTypes.DataTransferObjects.GAS,
                Constants.FuelTypes.DataTransferObjects.DIESEL
        );

        given(fuelTypeService.getAll()).willReturn(fuelTypes);

        verify(fuelTypeRepository).findAll();

        var response = fuelTypeService.getAll();

        assertEquals(2, response.size());
        assertEquals(Constants.FuelTypes.Labels.DIESEL, response.get(1).getFuelTypeName());
    }

    @Test
    void shouldAddFuelType() throws FuelTypeCreationException {
        var fuelType = Constants.FuelTypes.DataTransferObjects.DIESEL;
        var fuelTypeEntity = Constants.FuelTypes.Entities.DIESEL;

        given(fuelTypeMapper.dtoToEntity(fuelType)).willReturn(fuelTypeEntity);

        fuelTypeService.add(fuelType, bindingResult);

        fuelTypeCaptor = ArgumentCaptor.forClass(FuelTypeEntity.class);

        verify(fuelTypeRepository).save(fuelTypeCaptor.capture());

        assertThat(fuelTypeEntity).isEqualTo(fuelTypeCaptor.getValue());
        assertEquals(Constants.FuelTypes.Labels.DIESEL, fuelTypeCaptor.getValue().getFuelTypeName());
    }

    @Test
    void shouldUpdateFuelType() throws FuelTypeNotFoundException, FuelTypeCreationException {
        var id = 1L;
        var oldFuelType = Constants.FuelTypes.Entities.DIESEL;
        var updatedFuelType = FuelType.builder()
                .id(2L)
                .fuelTypeName("DIESEL-updated")
                .build();

        given(fuelTypeRepository.findById(id)).willReturn(Optional.of(oldFuelType));

        fuelTypeService.update(id, updatedFuelType, bindingResult);

        fuelTypeCaptor = ArgumentCaptor.forClass(FuelTypeEntity.class);

        verify(fuelTypeRepository).findById(id);
        verify(fuelTypeRepository).save(fuelTypeCaptor.capture());

        assertThat(oldFuelType).isEqualTo(fuelTypeCaptor.getValue());
        assertEquals("DIESEL-updated", fuelTypeCaptor.getValue().getFuelTypeName());
    }

    @Test
    void shouldDeleteFuelType() throws FuelTypeNotFoundException {
        var id = 2L;
        var currentFuelType = Constants.FuelTypes.Entities.DIESEL;

        given(fuelTypeRepository.findById(id)).willReturn(Optional.of(currentFuelType));

        fuelTypeService.delete(id);

        verify(fuelTypeRepository).findById(id);
        verify(fuelTypeRepository).deleteById(id);

        assertEquals(id, fuelTypeService.getById(id).getId());
    }
}
