package pl.malek.automi.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Gearbox;
import pl.malek.automi.Entities.GearboxEntity;
import pl.malek.automi.Exceptions.GearboxCreationException;
import pl.malek.automi.Exceptions.GearboxNotFoundException;

import java.util.List;

public interface GearboxService {
    Gearbox add(Gearbox gearbox, BindingResult result) throws GearboxCreationException;

    List<Gearbox> getAll();

    Gearbox delete(long id) throws GearboxNotFoundException;

    Gearbox update(long id, Gearbox gearbox, BindingResult result) throws GearboxCreationException, GearboxNotFoundException;

    void extractErrors(List<ObjectError> errors) throws GearboxCreationException;

    GearboxEntity getById(Long id) throws GearboxNotFoundException;
}
