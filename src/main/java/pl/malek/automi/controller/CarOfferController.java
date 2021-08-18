package pl.malek.automi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.dto.CarOffer;
import pl.malek.automi.exception.*;
import pl.malek.automi.service.CarOfferService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/car-offers")
public class CarOfferController {

    private final CarOfferService carOfferService;

    @GetMapping("/")
    public ResponseEntity<List<CarOffer>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(carOfferService.getAll());
    }

    @PostMapping("/")
    public ResponseEntity<CarOffer> add(@Valid @RequestBody CarOffer carOffer, BindingResult result) throws CarNotFoundException, CarOfferCreationException, MarkNotFoundException, UserNotFoundException, ColorNotFoundException, FuelTypeNotFoundException, ModelNotFoundException, GearboxNotFoundException, DrivingGearNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carOfferService.add(carOffer, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<CarOffer> delete(@PathVariable long id) throws CarOfferNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carOfferService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CarOffer> update(@PathVariable long id, @Valid @RequestBody CarOffer carOffer, BindingResult result) throws UserNotFoundException, CarOfferCreationException, CarNotFoundException, CarOfferNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carOfferService.update(id, carOffer, result));
    }

}
