package pl.malek.automi.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.Gearbox;
import pl.malek.automi.entity.GearboxEntity;
import pl.malek.automi.exception.GearboxCreationException;
import pl.malek.automi.exception.GearboxNotFoundException;

import java.util.List;

public interface GearboxService {
    Gearbox add(Gearbox gearbox, BindingResult result) throws GearboxCreationException;

    List<Gearbox> getAll();

    Gearbox delete(long id) throws GearboxNotFoundException;

    Gearbox update(long id, Gearbox gearbox, BindingResult result) throws GearboxCreationException, GearboxNotFoundException;

    void extractErrors(List<ObjectError> errors) throws GearboxCreationException;

    GearboxEntity getById(Long id) throws GearboxNotFoundException;
}
