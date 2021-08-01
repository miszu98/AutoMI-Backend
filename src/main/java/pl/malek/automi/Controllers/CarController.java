package pl.malek.automi.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.DTO.Car;
import pl.malek.automi.Exceptions.*;
import pl.malek.automi.Service.CarService;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private CarService carService;

    @GetMapping("/")
    public ResponseEntity<List<Car>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Car> add(@Valid @RequestBody Car car, BindingResult result) throws MarkNotFoundException, ColorNotFoundException, ModelNotFoundException, CarCreationException, FuelTypeNotFoundException, DrivingGearNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carService.add(car, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> delete(@PathVariable long id) throws CarNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable long id, @Valid @RequestBody Car car, BindingResult result) throws CarNotFoundException, MarkNotFoundException, ColorNotFoundException, ModelNotFoundException, CarCreationException, FuelTypeNotFoundException, DrivingGearNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carService.update(id, car, result));
    }
}
