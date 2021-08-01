package pl.malek.automi.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.DTO.Color;
import pl.malek.automi.Exceptions.ColorCreationException;
import pl.malek.automi.Exceptions.ColorNotFoundException;
import pl.malek.automi.Service.ColorService;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/colors")
public class ColorController {

    private final ColorService colorService;

    @GetMapping("/")
    public ResponseEntity<List<Color>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(colorService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Color> add(@Valid @RequestBody Color color, BindingResult result) throws ColorCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(colorService.add(color, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Color> delete(@PathVariable long id) throws ColorNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(colorService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Color> update(@PathVariable long id, @Valid @RequestBody Color color, BindingResult result) throws ColorNotFoundException, ColorCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(colorService.update(id, color, result));
    }
}
