package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.Gearbox;
import pl.malek.automi.entity.GearboxEntity;
import pl.malek.automi.exception.GearboxCreationException;
import pl.malek.automi.exception.GearboxNotFoundException;
import pl.malek.automi.mapper.GearboxMapper;
import pl.malek.automi.repository.GearboxRepository;
import pl.malek.automi.service.GearboxService;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class GearboxServiceImpl implements GearboxService {

    private final GearboxRepository gearboxRepository;
    private final GearboxMapper gearboxMapper;

    @Override
    public Gearbox add(Gearbox gearbox, BindingResult result) throws GearboxCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var drivingGearEntity = gearboxRepository.save(gearboxMapper.dtoToEntity(gearbox));
        return gearboxMapper.entityToDto(drivingGearEntity);
    }

    @Override
    public List<Gearbox> getAll() {
        return gearboxMapper.entitiesToDto(gearboxRepository.findAll());
    }

    @Override
    public Gearbox delete(long id) throws GearboxNotFoundException {
        var drivingGearEntity = getById(id);
        gearboxRepository.deleteById(id);
        return gearboxMapper.entityToDto(drivingGearEntity);
    }

    @Override
    public Gearbox update(long id, Gearbox gearbox, BindingResult result) throws GearboxCreationException, GearboxNotFoundException {
        var drivingGearEntity = getById(id);
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        drivingGearEntity.setGearbox(gearbox.getGearbox());
        gearboxRepository.save(drivingGearEntity);
        return gearboxMapper.entityToDto(drivingGearEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws GearboxCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new GearboxCreationException(messages);
    }

    @Override
    public GearboxEntity getById(Long id) throws GearboxNotFoundException {
        return gearboxRepository.findById(id).orElseThrow(
                () -> new GearboxNotFoundException(
                        String.format("Driving gear with id %d not found", id)
                )
        );
    }


}


