package pl.malek.automi.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Mark;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.Exceptions.MarkCreationException;
import pl.malek.automi.Exceptions.MarkNotFoundException;

import java.util.List;


public interface MarkService {
    Mark add(Mark mark, BindingResult result) throws MarkCreationException;

    List<Mark> getAll();

    Mark delete(long id);

    Mark update(long id, Mark mark, BindingResult result) throws MarkCreationException;

    void extractErrors(List<ObjectError> errors) throws MarkCreationException;

    MarkEntity getById(Long id) throws MarkNotFoundException;
}
