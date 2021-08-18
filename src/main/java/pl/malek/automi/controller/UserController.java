package pl.malek.automi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.dto.User;
import pl.malek.automi.exception.RoleNotFoundException;
import pl.malek.automi.exception.UserCreationException;
import pl.malek.automi.exception.UserNotFoundException;
import pl.malek.automi.service.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @GetMapping("/")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(userService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<User> register(@Valid @RequestBody User user, BindingResult result) throws RoleNotFoundException, UserCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.add(user, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<User> delete(@PathVariable long id) throws UserNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable long id, @Valid @RequestBody User user, BindingResult result) throws UserNotFoundException, RoleNotFoundException, UserCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(userService.update(id, user, result));
    }

}
