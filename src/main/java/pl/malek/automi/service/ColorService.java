package pl.malek.automi.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.Color;
import pl.malek.automi.entity.ColorEntity;
import pl.malek.automi.exception.ColorCreationException;
import pl.malek.automi.exception.ColorNotFoundException;

import java.util.List;

public interface ColorService {
    Color add(Color color, BindingResult result) throws ColorCreationException;

    List<Color> getAll();

    Color delete(long id) throws ColorNotFoundException;

    Color update(long id, Color color, BindingResult result) throws ColorCreationException, ColorNotFoundException;

    void extractErrors(List<ObjectError> errors) throws ColorCreationException;

    ColorEntity getById(Long id) throws ColorNotFoundException;
}
