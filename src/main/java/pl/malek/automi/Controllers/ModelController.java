package pl.malek.automi.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Exceptions.ModelCreationException;
import pl.malek.automi.Exceptions.ModelNotFoundException;
import pl.malek.automi.Service.ModelService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;

    @GetMapping("/page={page}/counts={counts}")
    public ResponseEntity<List<Model>> getAll(@PathVariable int page, @PathVariable int counts) {
        PageRequest pageRequest = PageRequest.of(page, counts);
        return ResponseEntity.status(HttpStatus.OK).body(modelService.getAll(pageRequest));
    }

    @PostMapping("/")
    public ResponseEntity<Model> add(@Valid @RequestBody Model model, BindingResult result) throws ModelCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.add(model, result));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Model> delete(@PathVariable long id) throws ModelNotFoundException {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.delete(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Model> update(@PathVariable long id, @Valid @RequestBody Model model, BindingResult result) throws ModelNotFoundException, ModelCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.update(id, model, result));
    }


}
