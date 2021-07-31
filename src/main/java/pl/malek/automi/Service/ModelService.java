package pl.malek.automi.Service;

import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Entities.ModelEntity;
import pl.malek.automi.Exceptions.MarkNotFoundException;
import pl.malek.automi.Exceptions.ModelCreationException;
import pl.malek.automi.Exceptions.ModelNotFoundException;


import java.util.List;

public interface ModelService {

    Model add(Model model, BindingResult result) throws ModelCreationException, MarkNotFoundException;

    List<Model> getAll();

    List<Model> getAll(Pageable pageable);

    Model delete(long id) throws ModelNotFoundException;

    Model update(long id, Model model, BindingResult result) throws ModelNotFoundException, ModelCreationException;

    void extractErrors(List<ObjectError> errors) throws ModelCreationException;

    ModelEntity getByName(String model) throws ModelNotFoundException;

}
