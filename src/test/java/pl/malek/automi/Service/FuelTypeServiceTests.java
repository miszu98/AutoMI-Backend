package pl.malek.automi.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.malek.automi.Constants;
import pl.malek.automi.Mappers.FuelTypeMapper;
import pl.malek.automi.Repository.FuelTypeRepository;
import pl.malek.automi.Service.Impl.FuelTypeServiceImpl;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class FuelTypeServiceTests {

    @Mock
    private FuelTypeRepository fuelTypeRepository;

    @Mock
    private FuelTypeMapper fuelTypeMapper;

    @InjectMocks
    private FuelTypeServiceImpl fuelTypeService;

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


}
