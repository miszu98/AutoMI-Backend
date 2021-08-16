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
import pl.malek.automi.DTO.Color;
import pl.malek.automi.Entities.ColorEntity;
import pl.malek.automi.Exceptions.ColorCreationException;
import pl.malek.automi.Exceptions.ColorNotFoundException;
import pl.malek.automi.Mappers.ColorMapper;
import pl.malek.automi.Repository.ColorRepository;
import pl.malek.automi.Service.Impl.ColorServiceImpl;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class ColorServiceTests {

    @Mock
    private ColorRepository colorRepository;

    @Mock
    private BindingResult bindingResult;

    @Mock
    private ColorMapper colorMapper;

    @Captor
    private ArgumentCaptor<ColorEntity> colorCaptor;

    @InjectMocks
    private ColorServiceImpl colorService;

    @Test
    void shouldGetAllColors() {
        var colors = List.of(
                Constants.Colors.DataTransferObjects.BLACK,
                Constants.Colors.DataTransferObjects.GREY
        );

        given(colorService.getAll()).willReturn(colors);

        verify(colorRepository).findAll();

        var response = colorService.getAll();

        assertEquals(2, response.size());
        assertEquals(Constants.Colors.Labels.BLACK,
                response.get(0).getColorName());
    }

    @Test
    void shouldAddColor() throws ColorCreationException {
        var newColor = Constants.Colors.DataTransferObjects.RED;

        given(colorMapper.dtoToEntity(newColor)).willReturn(Constants.Colors.Entities.RED);

        colorService.add(newColor, bindingResult);

        colorCaptor = ArgumentCaptor.forClass(ColorEntity.class);

        verify(colorRepository).save(colorCaptor.capture());


        assertEquals(Constants.Colors.Labels.RED, colorCaptor.getValue().getColorName());
    }

    @Test
    void shouldUpdateColor() throws ColorNotFoundException, ColorCreationException {
        var id = 1L;
        var old = Constants.Colors.Entities.BLACK;
        var updated = Color.builder().id(1L).colorName("PIANO BLACK").build();

        given(colorRepository.findById(id)).willReturn(Optional.of(old));

        colorService.update(id, updated, bindingResult);

        colorCaptor = ArgumentCaptor.forClass(ColorEntity.class);

        verify(colorRepository).findById(id);
        verify(colorRepository).save(colorCaptor.capture());

        assertThat(old).isEqualTo(colorCaptor.getValue());
        assertEquals("PIANO BLACK", colorCaptor.getValue().getColorName());
    }

    @Test
    void shouldDeleteColor() throws ColorNotFoundException {
        var id = 1L;

        given(colorRepository.findById(id))
                .willReturn(Optional.of(Constants.Colors.Entities.BLACK));

        colorService.delete(id);

        verify(colorRepository).findById(id);
        verify(colorRepository).deleteById(id);

        assertEquals(id, colorService.getById(id).getId());
    }
}
