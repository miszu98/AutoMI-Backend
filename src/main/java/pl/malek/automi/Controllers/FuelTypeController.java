package pl.malek.automi.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.DTO.FuelType;
import pl.malek.automi.Exceptions.FuelTypeCreationException;
import pl.malek.automi.Exceptions.FuelTypeNotFoundException;
import pl.malek.automi.Service.FuelTypeService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/fuel-types")
public class FuelTypeController {

    private final FuelTypeService fuelTypeService;

    @GetMapping("/")
    public ResponseEntity<List<FuelType>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(fuelTypeService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<FuelType> add(@Valid @RequestBody FuelType fuelType, BindingResult result) throws FuelTypeCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(fuelTypeService.add(fuelType, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<FuelType> delete(@PathVariable long id) throws FuelTypeNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(fuelTypeService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<FuelType> update(@PathVariable long id, @Valid @RequestBody FuelType fuelType, BindingResult result) throws FuelTypeNotFoundException, FuelTypeCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(fuelTypeService.update(id, fuelType, result));
    }
}
