package pl.malek.automi.Controllers;


import lombok.AllArgsConstructor;
import org.dom4j.rule.Mode;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.DTO.Mark;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Exceptions.MarkCreationException;
import pl.malek.automi.Exceptions.MarkNotFoundException;
import pl.malek.automi.Service.MarkService;

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

    @GetMapping("/{mark}")
    public ResponseEntity<List<Model>> getAllModels(@PathVariable String mark) {
        return ResponseEntity.status(HttpStatus.OK).body(markService.getByMark(mark));
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
