package pl.malek.automi.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Color;
import pl.malek.automi.Entities.ColorEntity;
import pl.malek.automi.Exceptions.ColorCreationException;
import pl.malek.automi.Exceptions.ColorNotFoundException;
import pl.malek.automi.Mappers.ColorMapper;
import pl.malek.automi.Repository.ColorRepository;
import pl.malek.automi.Service.ColorService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ColorServiceImpl implements ColorService {

    private final ColorRepository colorRepository;
    private final ColorMapper colorMapper;

    @Override
    public Color add(Color color, BindingResult result) throws ColorCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var drivingGearEntity = colorRepository.save(colorMapper.dtoToEntity(color));
        return colorMapper.entityToDto(drivingGearEntity);
    }

    @Override
    public List<Color> getAll() {
        return colorMapper.entitiesToDto(colorRepository.findAll());
    }

    @Override
    public Color delete(long id) throws ColorNotFoundException {
        var colorEntity = getById(id);
        colorRepository.deleteById(id);
        return colorMapper.entityToDto(colorEntity);
    }

    @Override
    public Color update(long id, Color color, BindingResult result) throws ColorCreationException, ColorNotFoundException {
        var colorEntity = getById(id);
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        colorEntity.setColorName(color.getColorName());
        colorRepository.save(colorEntity);
        return colorMapper.entityToDto(colorEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws ColorCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new ColorCreationException(messages);
    }

    @Override
    public ColorEntity getById(Long id) throws ColorNotFoundException {
        return colorRepository.findById(id).orElseThrow(
                () -> new ColorNotFoundException(
                        String.format("Color with id %d not found", id)
                )
        );
    }
}
