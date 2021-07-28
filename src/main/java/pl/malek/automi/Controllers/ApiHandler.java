package pl.malek.automi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.malek.automi.Exceptions.RoleCreationException;
import pl.malek.automi.Exceptions.RoleNotFoundException;
import pl.malek.automi.Exceptions.UserCreationException;
import pl.malek.automi.Exceptions.UserNotFoundException;

import java.util.List;

@RestControllerAdvice
public class ApiHandler {

    // Roles exceptions

    @ExceptionHandler(RoleCreationException.class)
    public ResponseEntity<List<String>> handleCreationRoleException(RoleCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFoundException(RoleNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    // User exceptions

    @ExceptionHandler(UserCreationException.class)
    public ResponseEntity<List<String>> handleCreationUserException(UserCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    @ExceptionHandler(UserNotFoundException.class)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }



}
