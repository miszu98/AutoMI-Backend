package pl.malek.automi.Service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.User;
import pl.malek.automi.Exceptions.RoleNotFoundException;
import pl.malek.automi.Exceptions.UserCreationException;
import pl.malek.automi.Exceptions.UserNotFoundException;

import java.util.List;

public interface UserService {
    User add(User user, BindingResult result) throws UserCreationException, RoleNotFoundException;

    List<User> getAll();

    User delete(long id) throws UserNotFoundException;

    User update(long id, User user, BindingResult result) throws UserNotFoundException, UserCreationException, RoleNotFoundException;

    void extractErrors(List<ObjectError> errors) throws UserCreationException;
}
