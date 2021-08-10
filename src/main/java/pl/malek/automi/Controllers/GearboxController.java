package pl.malek.automi.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.DTO.Gearbox;
import pl.malek.automi.Exceptions.GearboxCreationException;
import pl.malek.automi.Exceptions.GearboxNotFoundException;
import pl.malek.automi.Service.GearboxService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/driving-gears")
public class GearboxController {

    private final GearboxService gearboxService;

    @GetMapping("/")
    public ResponseEntity<List<Gearbox>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(gearboxService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Gearbox> add(@Valid @RequestBody Gearbox gearbox, BindingResult result) throws GearboxCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(gearboxService.add(gearbox, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Gearbox> delete(@PathVariable long id) throws GearboxNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(gearboxService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Gearbox> update(@PathVariable long id, @Valid @RequestBody Gearbox gearbox, BindingResult result) throws GearboxCreationException, GearboxNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(gearboxService.update(id, gearbox, result));
    }

}
