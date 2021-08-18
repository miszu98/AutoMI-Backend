package pl.malek.automi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.dto.Car;
import pl.malek.automi.exception.*;
import pl.malek.automi.service.CarService;
import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/cars")
public class CarController {

    private final CarService carService;

    @GetMapping("/")
    public ResponseEntity<List<Car>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(carService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<Car> add(@Valid @RequestBody Car car, BindingResult result) throws MarkNotFoundException, ColorNotFoundException, ModelNotFoundException, CarCreationException, FuelTypeNotFoundException, GearboxNotFoundException, DrivingGearNotFoundException {
        System.out.println(car);
        return ResponseEntity.status(HttpStatus.OK).body(carService.add(car, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Car> delete(@PathVariable long id) throws CarNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Car> update(@PathVariable long id, @Valid @RequestBody Car car, BindingResult result) throws CarNotFoundException, MarkNotFoundException, ColorNotFoundException, ModelNotFoundException, CarCreationException, FuelTypeNotFoundException, GearboxNotFoundException, DrivingGearNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carService.update(id, car, result));
    }
}
