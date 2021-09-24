package pl.malek.automi.controller;


import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.dto.Mark;
import pl.malek.automi.dto.Model;
import pl.malek.automi.exception.MarkCreationException;
import pl.malek.automi.exception.MarkNotFoundException;
import pl.malek.automi.service.MarkService;

import javax.validation.Valid;
import java.util.List;


@RestController
@AllArgsConstructor
@RequestMapping("/marks")
public class MarkController {

    private final MarkService markService;

    @GetMapping("/")
    public ResponseEntity<List<Mark>> getAll() {
        return ResponseEntity.status(HttpStatus.OK).body(markService.getAll());
    }

    @GetMapping("/{mark}/name")
    public ResponseEntity<List<Model>> getAllModels(@PathVariable String mark) {
        return ResponseEntity.status(HttpStatus.OK).body(markService.getByMark(mark));
    }

    @GetMapping("/{id}")
    public ResponseEntity<List<Model>> getAllModels(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(markService.getByMarkId(id));
    }

    @PostMapping("/")
    public ResponseEntity<Mark> add(@Valid @RequestBody Mark mark, BindingResult result) throws MarkCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(markService.add(mark, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Mark> delete(@PathVariable long id) throws MarkNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(markService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Mark> update(@PathVariable long id, @Valid @RequestBody Mark mark, BindingResult result) throws MarkCreationException, MarkNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(markService.update(id, mark, result));
    }



}
