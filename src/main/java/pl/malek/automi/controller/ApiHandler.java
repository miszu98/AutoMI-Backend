package pl.malek.automi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pl.malek.automi.exception.*;

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

    // Model exceptions

    @ExceptionHandler(ModelNotFoundException.class)
    public ResponseEntity<String> handleModelNotFoundException(ModelNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ModelCreationException.class)
    public ResponseEntity<List<String>> handleCreationModelException(ModelCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    // Mark exceptions

    @ExceptionHandler(MarkNotFoundException.class)
    public ResponseEntity<String> handleMarkNotFoundException(MarkNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(MarkCreationException.class)
    public ResponseEntity<List<String>> handleCreationMarkException(MarkCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    // Fuel types exceptions

    @ExceptionHandler(FuelTypeNotFoundException.class)
    public ResponseEntity<String> handleFuelTypeNotFoundException(FuelTypeNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(FuelTypeCreationException.class)
    public ResponseEntity<List<String>> handleCreationFuelTypeException(FuelTypeCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    // Gearboxes exceptions

    @ExceptionHandler(GearboxNotFoundException.class)
    public ResponseEntity<String> handleGearboxNotFoundException(GearboxNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(GearboxCreationException.class)
    public ResponseEntity<List<String>> handleCreationGearboxException(GearboxCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    // Driving gears exceptions

    @ExceptionHandler(DrivingGearNotFoundException.class)
    public ResponseEntity<String> handleDrivingGearNotFoundException(DrivingGearNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(DrivingGearCreationException.class)
    public ResponseEntity<List<String>> handleCreationDrivingGearException(DrivingGearCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    // Colors exceptions

    @ExceptionHandler(ColorNotFoundException.class)
    public ResponseEntity<String> handleColorNotFoundException(ColorNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(ColorCreationException.class)
    public ResponseEntity<List<String>> handleCreationColorException(ColorCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }



    // Car exceptions

    @ExceptionHandler(CarNotFoundException.class)
    public ResponseEntity<String> handleCarNotFoundException(CarNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }

    @ExceptionHandler(CarCreationException.class)
    public ResponseEntity<List<String>> handleCreationCarException(CarCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    // Car offers exceptions

    @ExceptionHandler(CarOfferCreationException.class)
    public ResponseEntity<List<String>> handleCreationCarOfferException(CarOfferCreationException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessages());
    }

    @ExceptionHandler(CarOfferNotFoundException.class)
    public ResponseEntity<String> handleCarOfferNotFoundException(CarOfferNotFoundException exception) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(exception.getMessage());
    }



}
