package pl.malek.automi.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Mark;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.Exceptions.MarkCreationException;
import pl.malek.automi.Exceptions.MarkNotFoundException;
import pl.malek.automi.Mappers.MarkMapper;
import pl.malek.automi.Mappers.ModelMapper;
import pl.malek.automi.Repository.MarkRepository;
import pl.malek.automi.Service.MarkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;

    @Override
    public List<Model> getByMark(String mark) {
        Optional<MarkEntity> optionalMarkEntity = markRepository.findMarkEntityByMark(mark);
        MarkEntity founded = optionalMarkEntity.orElseThrow();
        return ModelMapper.entitiesToDto(founded.getModels());
    }

    @Override
    public Mark add(Mark mark, BindingResult result) throws MarkCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var markEntity = markRepository.save(MarkMapper.dtoToEntity(mark));
        return MarkMapper.entityToDto(markEntity);
    }

    @Override
    public List<Mark> getAll() {
        return MarkMapper.entitiesToDto(markRepository.findAll());
    }

    @Override
    public Mark delete(long id) throws MarkNotFoundException {
        var markEntity = getById(id);
        markRepository.deleteById(id);
        return MarkMapper.entityToDto(markEntity);
    }

    @Override
    public Mark update(long id, Mark mark, BindingResult result) throws MarkCreationException, MarkNotFoundException {
        var markEntity = getById(id);
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        markEntity.setMark(mark.getMark());
        markRepository.save(markEntity);
        return MarkMapper.entityToDto(markEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws MarkCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new MarkCreationException(messages);
    }

    @Override
    public MarkEntity getById(Long id) throws MarkNotFoundException {
        return markRepository.findById(id).orElseThrow(
                () -> new MarkNotFoundException(
                        String.format("Mark with id %d not found", id)
                )
        );
    }
}
