package pl.malek.automi.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.DTO.DrivingGear;
import pl.malek.automi.Entities.DrivingGearEntity;
import pl.malek.automi.Exceptions.DrivingGearCreationException;
import pl.malek.automi.Exceptions.DrivingGearNotFoundException;
import pl.malek.automi.Mappers.DrivingGearMapper;
import pl.malek.automi.Repository.DrivingGearRepository;
import pl.malek.automi.Service.Impl.DrivingGearServiceImpl;
import pl.malek.automi.UtilsMI.Constants;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class DrivingGearServiceTests {

    @Mock
    private DrivingGearRepository drivingGearRepository;

    @Mock
    private DrivingGearMapper drivingGearMapper;

    @InjectMocks
    private DrivingGearServiceImpl drivingGearService;

    @Mock
    private BindingResult bindingResult;

    @Captor
    private ArgumentCaptor<DrivingGearEntity> drivingGearCaptor;

    @Test
    void shouldReturnAllDrivingGears() {
        var drivingGears = List.of(
                Constants.DrivingGears.DataTransferObjects.AWD,
                Constants.DrivingGears.DataTransferObjects.RWD,
                Constants.DrivingGears.DataTransferObjects.FWD
        );

        given(drivingGearService.getAll()).willReturn(drivingGears);

        verify(drivingGearRepository).findAll();

        var response = drivingGearService.getAll();

        assertEquals(3, response.size());
        assertEquals(Constants.DrivingGears.Labels.AWD, response.get(0).getDrivingGear());
    }

    @Test
    void shouldAddDrivingGear() throws DrivingGearCreationException {
        var newDrivingGear = Constants.DrivingGears.DataTransferObjects.AWD;
        var newDrivingGearEntity = Constants.DrivingGears.Entities.AWD;

        given(drivingGearMapper.dtoToEntity(newDrivingGear))
                .willReturn(newDrivingGearEntity);

        drivingGearService.add(newDrivingGear, bindingResult);

        drivingGearCaptor = ArgumentCaptor.forClass(DrivingGearEntity.class);

        verify(drivingGearRepository).save(drivingGearCaptor.capture());

        var arg = drivingGearCaptor.getValue();

        assertThat(arg).isEqualTo(newDrivingGearEntity);
        assertEquals(Constants.DrivingGears.Labels.AWD, arg.getDrivingGear());
    }

    @Test
    void shouldUpdateDrivingGear() throws DrivingGearCreationException, DrivingGearNotFoundException {
        var id = 3L;
        var oldDrivingGear = Constants.DrivingGears.Entities.AWD;
        var updatedDrivingGear = DrivingGear.builder()
                .id(3L)
                .drivingGear("4WD")
                .build();

        given(drivingGearRepository.findById(id))
                .willReturn(Optional.of(oldDrivingGear));

        drivingGearService.update(id, updatedDrivingGear, bindingResult);

        drivingGearCaptor = ArgumentCaptor.forClass(DrivingGearEntity.class);

        verify(drivingGearRepository).save(drivingGearCaptor.capture());

        var arg = drivingGearCaptor.getValue();

        assertThat(arg).isEqualTo(oldDrivingGear);
        assertEquals("4WD", arg.getDrivingGear());
    }

    @Test
    void shouldDeleteDrivingGear() throws DrivingGearNotFoundException {
        var id = 3L;
        var drivingGearEntity = Constants.DrivingGears.Entities.AWD;

        given(drivingGearRepository.findById(id))
                .willReturn(Optional.of(drivingGearEntity));

        drivingGearService.delete(id);

        verify(drivingGearRepository).deleteById(id);
        verify(drivingGearRepository).findById(id);

        assertEquals(id, drivingGearService.getById(id).getId());
    }
}
