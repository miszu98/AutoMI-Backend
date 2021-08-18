package pl.malek.automi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.utils.Constants;
import pl.malek.automi.dto.Gearbox;
import pl.malek.automi.entity.GearboxEntity;
import pl.malek.automi.exception.GearboxCreationException;
import pl.malek.automi.exception.GearboxNotFoundException;
import pl.malek.automi.mapper.GearboxMapper;
import pl.malek.automi.repository.GearboxRepository;
import pl.malek.automi.service.Impl.GearboxServiceImpl;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class GearboxesServiceTests {

    @Mock
    private GearboxRepository gearboxRepository;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private GearboxMapper gearboxMapper;

    @Captor
    private ArgumentCaptor<GearboxEntity> gearboxCaptor;

    @InjectMocks
    private GearboxServiceImpl gearboxService;

    @Test
    void shouldReturnAllGearbox() {
        var gearboxes = List.of(
                Constants.Gearboxes.DataTransferObjects.AUTOMATIC,
                Constants.Gearboxes.DataTransferObjects.MANUAL
        );

        given(gearboxService.getAll()).willReturn(gearboxes);

        verify(gearboxRepository).findAll();

        var response = gearboxService.getAll();

        assertEquals(2, response.size());
        assertEquals(Constants.Gearboxes.Labels.MANUAL,
                response.get(1).getGearbox());
    }

    @Test
    void shouldAddGearbox() throws GearboxCreationException {
        var newGearbox = Constants.Gearboxes.DataTransferObjects.TIPTRONIC;
        var gearboxEntity = Constants.Gearboxes.Entities.TIPTRONIC;

        given(gearboxMapper.dtoToEntity(newGearbox))
                .willReturn(gearboxEntity);

        gearboxService.add(newGearbox, bindingResult);

        gearboxCaptor = ArgumentCaptor.forClass(GearboxEntity.class);

        verify(gearboxRepository).save(gearboxCaptor.capture());

        assertThat(gearboxEntity).isEqualTo(gearboxCaptor.getValue());
        assertEquals(3, gearboxCaptor.getValue().getId());
    }

    @Test
    void shouldUpdateGearbox() throws GearboxCreationException, GearboxNotFoundException {
        var id = 1L;
        var old = Constants.Gearboxes.Entities.AUTOMATIC;
        var updatedName = "AUTOMAT";
        var updated = Gearbox.builder().id(1L)
                .gearbox(updatedName).build();

        given(gearboxRepository.findById(id)).willReturn(Optional.of(old));

        gearboxService.update(id, updated, bindingResult);

        gearboxCaptor = ArgumentCaptor.forClass(GearboxEntity.class);

        verify(gearboxRepository).findById(id);
        verify(gearboxRepository).save(gearboxCaptor.capture());

        assertThat(old).isEqualTo(gearboxCaptor.getValue());
        assertEquals(updatedName, gearboxCaptor.getValue().getGearbox());
    }

    @Test
    void shouldDeleteGearbox() throws GearboxNotFoundException {
        var id = 2L;
        var objectToDelete = Constants.Gearboxes.Entities.MANUAL;

        given(gearboxRepository.findById(id)).willReturn(Optional.of(objectToDelete));

        gearboxService.delete(id);

        verify(gearboxRepository).findById(id);
        verify(gearboxRepository).deleteById(id);

        assertEquals(id, gearboxService.getById(id).getId());
    }
}
