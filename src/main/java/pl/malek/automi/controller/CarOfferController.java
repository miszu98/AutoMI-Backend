package pl.malek.automi.controller;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.dto.CarOffer;
import pl.malek.automi.exception.*;
import pl.malek.automi.service.CarOfferService;
import javax.validation.Valid;
import java.util.List;
import java.util.Map;

@RestController
@AllArgsConstructor
@RequestMapping("/car-offers")
public class CarOfferController {

    private final CarOfferService carOfferService;

    @GetMapping("/{count}/page={page}/")
    public ResponseEntity<Iterable<CarOffer>> getOffers(@PathVariable int page, @PathVariable int count) {
        PageRequest pageRequest = PageRequest.of(page, count);
        return ResponseEntity.status(HttpStatus.OK).body(carOfferService.getAll(pageRequest));
    }

    @PostMapping("/filter/{size}/page={page}")
    public ResponseEntity<?> filterOffers(@RequestBody Map<String, Object> params, @PathVariable int size, @PathVariable int page) {
        return ResponseEntity.status(HttpStatus.OK).body(carOfferService.filter(params, PageRequest.of(page, size)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CarOffer> getOffer(@PathVariable long id) throws CarOfferNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(carOfferService.getOfferById(id));
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
    public ResponseEntity<CarOffer> update(@PathVariable long id, @Valid @RequestBody CarOffer carOffer, BindingResult result) throws UserNotFoundException, CarOfferCreationException, CarNotFoundException, CarOfferNotFoundException, ColorNotFoundException, MarkNotFoundException, DrivingGearNotFoundException, ModelNotFoundException, FuelTypeNotFoundException, GearboxNotFoundException, CarCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(carOfferService.update(id, carOffer, result));
    }

    @GetMapping("/{id}/user/")
    public ResponseEntity<List<CarOffer>> getOffersByUser(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(carOfferService.getOffersByUser(id));
    }

}
