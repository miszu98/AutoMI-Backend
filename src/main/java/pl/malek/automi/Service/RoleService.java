package pl.malek.automi.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Role;
import pl.malek.automi.Entities.RoleEntity;
import pl.malek.automi.Exceptions.RoleCreationException;
import pl.malek.automi.Exceptions.RoleNotFoundException;

import java.util.List;

public interface RoleService {

    Role add(Role role, BindingResult result) throws RoleCreationException;

    List<Role> getAll();

    Role delete(long id) throws RoleNotFoundException;

    Role update(long id, Role role, BindingResult result) throws RoleNotFoundException, RoleCreationException;

    void extractErrors(List<ObjectError> errors) throws RoleCreationException;

    RoleEntity getByName(String roleName) throws RoleNotFoundException;

    RoleEntity getById(Long id) throws RoleNotFoundException;
}
