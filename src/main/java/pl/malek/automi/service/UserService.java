package pl.malek.automi.service;

import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.User;
import pl.malek.automi.entity.UserEntity;
import pl.malek.automi.exception.RoleNotFoundException;
import pl.malek.automi.exception.UserCreationException;
import pl.malek.automi.exception.UserNotFoundException;

import java.util.List;

public interface UserService {
    User add(User user, BindingResult result)
            throws UserCreationException, RoleNotFoundException;

    User delete(long id) throws UserNotFoundException;

    User update(long id, User user, BindingResult result)
            throws UserNotFoundException, UserCreationException, RoleNotFoundException;

    void extractErrors(List<ObjectError> errors) throws UserCreationException;

    UserEntity getById(long id) throws UserNotFoundException;

    Long getIdByEmail(String email);

    boolean changePassword(String uuid, String newPassword);
}


