package pl.malek.automi.Controllers;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Exceptions.ModelCreationException;
import pl.malek.automi.Mappers.ModelMapper;
import pl.malek.automi.Service.ModelService;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("/models")
public class ModelController {

    private final ModelService modelService;

    private static final int COUNTS = 1;

    @GetMapping("/{page}")
    public ResponseEntity<List<Model>> getAll(@PathVariable int page) {
        PageRequest pageRequest = PageRequest.of(page, COUNTS);
        return ResponseEntity.status(HttpStatus.OK).body(modelService.getAll(pageRequest));
    }


    @PostMapping("/")
    public ResponseEntity<Model> add(@Valid @RequestBody Model model, BindingResult result) throws ModelCreationException {
        return ResponseEntity.status(HttpStatus.OK).body(modelService.add(model, result));
    }


}
