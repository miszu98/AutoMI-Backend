package pl.malek.automi.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.utils.Constants;
import pl.malek.automi.dto.Mark;
import pl.malek.automi.entity.MarkEntity;
import pl.malek.automi.exception.MarkCreationException;
import pl.malek.automi.exception.MarkNotFoundException;
import pl.malek.automi.mapper.MarkMapper;
import pl.malek.automi.repository.MarkRepository;
import pl.malek.automi.service.Impl.MarkServiceImpl;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class MarkServiceTests {

    @Mock
    private MarkRepository markRepository;

    @Mock
    private MarkMapper markMapper;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private MarkServiceImpl markService;

    @Test
    void checkIfMocksWorks() {
        assertNotNull(markRepository);
        assertNotNull(markMapper);
        assertNotNull(markService);
    }

    @Test
    void shouldReturnAllMarks() {
        var marks = List.of(
                Constants.Marks.DataTransferObjects.MERCEDES_BENZ,
                Constants.Marks.DataTransferObjects.BMW,
                Constants.Marks.DataTransferObjects.AUDI
        );

        given(markService.getAll()).willReturn(marks);

        verify(markRepository).findAll();

        var response = markService.getAll();

        assertEquals(3, response.size());
        assertEquals(Constants.Marks.Labels.BMW, response.get(1).getMark());
    }

    @Test
    void shouldAddMark() throws MarkCreationException {
        var newMark = Constants.Marks.DataTransferObjects.BMW;
        var markEntity = MarkEntity.builder().id(1L).mark(Constants.Marks.Labels.BMW).build();

        given(markMapper.dtoToEntity(newMark))
                .willReturn(markEntity);

        markService.add(newMark, bindingResult);

        var markArgumentCaptor =
                ArgumentCaptor.forClass(MarkEntity.class);

        verify(markRepository).save(markArgumentCaptor.capture());

        assertThat(markEntity)
                .isEqualTo(markArgumentCaptor.getValue());
        assertEquals(1, markArgumentCaptor.getValue().getId());
        assertEquals(Constants.Marks.Labels.BMW,
                markArgumentCaptor.getValue().getMark());
    }

    @Test
    void shouldUpdateMark() throws MarkCreationException, MarkNotFoundException {
        var id = 1L;
        var oldMark = Constants.Marks.DataTransferObjects.BMW;
        var updatedMark = Mark.builder().mark("Bawaria Motors").build();

        given(markRepository.findById(id))
                .willReturn(Optional.of(Constants.Marks.Entities.BMW_ENTITY));

        markService.update(id, updatedMark, bindingResult);

        var markArgumentCaptor =
                ArgumentCaptor.forClass(MarkEntity.class);

        verify(markRepository).findById(id);
        verify(markRepository).save(markArgumentCaptor.capture());

        assertThat(Constants.Marks.Entities.BMW_ENTITY)
                .isEqualTo(markArgumentCaptor.getValue())
        ;
        assertEquals("Bawaria Motors",
                markArgumentCaptor.getValue().getMark());
        assertNotEquals(oldMark.getMark(), markArgumentCaptor.getValue().getMark());
    }

    @Test
    void shouldDeleteMark() throws MarkNotFoundException {
        var id = 1L;

        given(markRepository.findById(id))
                .willReturn(Optional.of(Constants.Marks.Entities.BMW_ENTITY));

        markService.delete(id);

       verify(markRepository).deleteById(id);

       assertEquals(id, markService.getById(id).getId());
    }
}
