package pl.malek.automi.service;

import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.Model;
import pl.malek.automi.entity.ModelEntity;
import pl.malek.automi.exception.MarkNotFoundException;
import pl.malek.automi.exception.ModelCreationException;
import pl.malek.automi.exception.ModelNotFoundException;


import java.util.List;

public interface ModelService {

    Model add(Model model, BindingResult result) throws ModelCreationException, MarkNotFoundException;

    List<Model> getAll();

    List<Model> getAll(Pageable pageable);

    Model delete(long id) throws ModelNotFoundException;

    Model update(long id, Model model, BindingResult result) throws ModelNotFoundException, ModelCreationException;

    void extractErrors(List<ObjectError> errors) throws ModelCreationException;

    ModelEntity getById(long id) throws ModelNotFoundException;

}
