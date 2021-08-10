package pl.malek.automi.Controllers;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.DTO.CarOffer;
import pl.malek.automi.Exceptions.*;
import pl.malek.automi.Service.CarOfferService;

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
    public ResponseEntity<CarOffer> add(@Valid @RequestBody CarOffer carOffer, BindingResult result) throws CarNotFoundException, CarOfferCreationException, MarkNotFoundException, UserNotFoundException, ColorNotFoundException, FuelTypeNotFoundException, ModelNotFoundException, GearboxNotFoundException {
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
