package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.Mark;
import pl.malek.automi.dto.Model;
import pl.malek.automi.entity.MarkEntity;
import pl.malek.automi.exception.MarkCreationException;
import pl.malek.automi.exception.MarkNotFoundException;
import pl.malek.automi.mapper.MarkMapper;
import pl.malek.automi.mapper.ModelMapper;
import pl.malek.automi.repository.MarkRepository;
import pl.malek.automi.service.MarkService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class MarkServiceImpl implements MarkService {

    private final MarkRepository markRepository;
    private final MarkMapper markMapper;
    private final ModelMapper modelMapper;

    @Override
    public List<Model> getByMark(String mark) {
        Optional<MarkEntity> optionalMarkEntity = markRepository.findMarkEntityByMark(mark);
        MarkEntity founded = optionalMarkEntity.orElseThrow();
        return modelMapper.entitiesToDto(founded.getModels());
    }

    @Override
    public Mark add(Mark mark, BindingResult result) throws MarkCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var markEntity = markRepository.save(markMapper.dtoToEntity(mark));
        return markMapper.entityToDto(markEntity);
    }

    @Override
    public List<Mark> getAll() {
        return markMapper.entitiesToDto(markRepository.findAll());
    }

    @Override
    public Mark delete(long id) throws MarkNotFoundException {
        var markEntity = getById(id);
        markRepository.deleteById(id);
        return markMapper.entityToDto(markEntity);
    }

    @Override
    public Mark update(long id, Mark mark, BindingResult result) throws MarkCreationException, MarkNotFoundException {
        var markEntity = getById(id);
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        markEntity.setMark(mark.getMark());
        markRepository.save(markEntity);
        return markMapper.entityToDto(markEntity);
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
