package pl.malek.automi.Service;

import org.checkerframework.checker.units.qual.C;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.Constants;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Entities.ModelEntity;
import pl.malek.automi.Exceptions.MarkNotFoundException;
import pl.malek.automi.Exceptions.ModelCreationException;
import pl.malek.automi.Exceptions.ModelNotFoundException;
import pl.malek.automi.Mappers.ModelMapper;
import pl.malek.automi.Repository.ModelRepository;
import pl.malek.automi.Service.Impl.ModelServiceImpl;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ModelServiceTests {

    @Mock
    private ModelRepository modelRepository;

    @Mock
    private ModelMapper modelMapper;

    @Mock
    private BindingResult bindingResult;

    @Captor
    private ArgumentCaptor<ModelEntity> modelCaptor;

    @InjectMocks
    private ModelServiceImpl modelService;

    @Test
    void checkIfMocksWorks() {
        assertNotNull(modelRepository);
        assertNotNull(modelService);
    }

    @Test
    void shouldReturnAllModels() {
        var models = List.of(
                Constants.Models.DataTransferObjects.C63S,
                Constants.Models.DataTransferObjects.M5
        );

        given(modelService.getAll()).willReturn(models);

        verify(modelRepository).findAll();

        var response = modelService.getAll();

        assertEquals(2, response.size());
        assertEquals("C63s", response.get(0).getModel());
    }

    @Test
    void shouldAddModel() throws ModelCreationException, MarkNotFoundException {
        var newModel = Constants.Models.DataTransferObjects.C63S;

        given(modelMapper.dtoToEntity(newModel)).willReturn(Constants.Models.Entities.C63S);

        modelService.add(newModel, bindingResult);

        modelCaptor = ArgumentCaptor.forClass(ModelEntity.class);

        verify(modelRepository).save(modelCaptor.capture());

        assertEquals("C63s", modelCaptor.getValue().getModel());
        assertEquals(newModel.getMarkId(), modelCaptor.getValue().getMarkEntity().getId());
    }

    @Test
    void shouldUpdateModel() throws ModelNotFoundException, ModelCreationException {
        var id = 2L;
        var updatedModelName = Constants.Models.Labels.M5CS;
        var oldModel = Constants.Models.Entities.M5;
        var updatedModel = Model.builder().id(2L).model(updatedModelName).markId(2L).build();

        given(modelRepository.findById(id)).willReturn(Optional.of(oldModel));

        modelService.update(id, updatedModel, bindingResult);

        modelCaptor = ArgumentCaptor.forClass(ModelEntity.class);

        verify(modelRepository).findById(id);
        verify(modelRepository).save(modelCaptor.capture());

        assertThat(oldModel).isEqualTo(modelCaptor.getValue());
        assertEquals(updatedModelName, modelCaptor.getValue().getModel());
    }

    @Test
    void shouldDeleteModel() throws ModelNotFoundException {
        var id = 2L;
        var returnedModel = Constants.Models.Entities.M5;

        given(modelRepository.findById(id)).willReturn(Optional.of(returnedModel));

        modelService.delete(id);

        verify(modelRepository).findById(id);
        verify(modelRepository).deleteById(id);

        assertEquals(2, modelService.getById(id).getId());
    }
}
