package pl.malek.automi.Service.Impl;


import lombok.AllArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Entities.ModelEntity;
import pl.malek.automi.Exceptions.MarkNotFoundException;
import pl.malek.automi.Exceptions.ModelCreationException;
import pl.malek.automi.Exceptions.ModelNotFoundException;
import pl.malek.automi.Mappers.ModelMapper;
import pl.malek.automi.Repository.ModelRepository;
import pl.malek.automi.Service.ModelService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ModelServiceImpl implements ModelService {

    private final ModelRepository modelRepository;
    private final ModelMapper modelMapper;

    @Override
    public Model add(Model model, BindingResult result) throws ModelCreationException, MarkNotFoundException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var modelEntity = modelRepository.save(modelMapper.dtoToEntity(model));
        return ModelMapper.entityToDto(modelEntity);
    }

    @Override
    public Model update(long id, Model model, BindingResult result) throws ModelNotFoundException, ModelCreationException {
        var modelEntity = modelRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException(
                        "Model with id: " + id + " not found"
                )
        );
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        modelEntity.setModel(model.getModel());
        modelRepository.save(modelEntity);
        return ModelMapper.entityToDto(modelEntity);
    }

    @Override
    public List<Model> getAll() {
        return ModelMapper.entitiesToDto(modelRepository.findAll());
    }

    @Override
    public List<Model> getAll(Pageable pageable) {
        return ModelMapper.entitiesToDto(modelRepository.findAll(pageable));
    }

    @Override
    public Model delete(long id) throws ModelNotFoundException {
        var modelEntity = modelRepository.findById(id).orElseThrow(
                () -> new ModelNotFoundException(
                        "Model with id: " + id + " not found"
                )
        );
        modelRepository.deleteById(id);
        return ModelMapper.entityToDto(modelEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws ModelCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new ModelCreationException(messages);
    }

    @Override
    public ModelEntity getByName(String model) throws ModelNotFoundException {
        return modelRepository.findByModel(model).orElseThrow(
                () -> new ModelNotFoundException(
                        "Model with name: " + model + " not found"
                )
        );
    }
}
