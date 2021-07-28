package pl.malek.automi.Controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.malek.automi.Exceptions.RoleCreationException;
import pl.malek.automi.Exceptions.RoleNotFoundException;

import java.util.List;

@RestControllerAdvice
public class ApiHandler {

    @ExceptionHandler(RoleCreationException.class)
    public ResponseEntity<List<String>> handleCreationRoleException(RoleCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    @ExceptionHandler(RoleNotFoundException.class)
    public ResponseEntity<String> handleRoleNotFoundException(RoleNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }
}
