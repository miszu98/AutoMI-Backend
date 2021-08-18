package pl.malek.automi.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.Mark;
import pl.malek.automi.dto.Model;
import pl.malek.automi.entity.MarkEntity;
import pl.malek.automi.exception.MarkCreationException;
import pl.malek.automi.exception.MarkNotFoundException;

import java.util.List;


public interface MarkService {
    List<Model> getByMark(String mark);

    Mark add(Mark mark, BindingResult result) throws MarkCreationException;

    List<Mark> getAll();

    Mark delete(long id) throws MarkNotFoundException;

    Mark update(long id, Mark mark, BindingResult result) throws MarkCreationException, MarkNotFoundException;

    void extractErrors(List<ObjectError> errors) throws MarkCreationException;

    MarkEntity getById(Long id) throws MarkNotFoundException;
}
