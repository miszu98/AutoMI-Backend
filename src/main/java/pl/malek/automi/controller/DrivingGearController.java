package pl.malek.automi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.dto.DrivingGear;
import pl.malek.automi.exception.*;
import pl.malek.automi.service.DrivingGearService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/driving-gears")
public class DrivingGearController {

    private DrivingGearService drivingGearService;

    @GetMapping("/")
    public ResponseEntity<List<DrivingGear>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(drivingGearService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<DrivingGear> add(@Valid @RequestBody DrivingGear drivingGear, BindingResult result) throws DrivingGearCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(drivingGearService.add(drivingGear, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<DrivingGear> delete(@PathVariable long id) throws DrivingGearNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(drivingGearService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DrivingGear> update(@PathVariable long id, @Valid @RequestBody DrivingGear drivingGear, BindingResult result) throws DrivingGearNotFoundException, DrivingGearCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(drivingGearService.update(id, drivingGear, result));
    }
}
