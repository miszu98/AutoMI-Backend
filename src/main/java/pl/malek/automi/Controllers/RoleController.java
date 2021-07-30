package pl.malek.automi.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.DTO.Role;
import pl.malek.automi.Exceptions.RoleCreationException;
import pl.malek.automi.Exceptions.RoleNotFoundException;
import pl.malek.automi.Service.RoleService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/roles")
public class RoleController {

    private RoleService roleService;

    @PostMapping("/")
    public ResponseEntity<Role> add(@Valid @RequestBody Role role, BindingResult result) throws RoleCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.add(role, result));
    }

    @GetMapping("/")
    public ResponseEntity<List<Role>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.getAll());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Role> delete(@PathVariable long id) throws RoleNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Role> update(@PathVariable long id, @Valid @RequestBody Role role, BindingResult result) throws RoleNotFoundException, RoleCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(roleService.update(id, role, result));
    }

}
