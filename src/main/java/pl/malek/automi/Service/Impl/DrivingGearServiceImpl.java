package pl.malek.automi.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.DrivingGear;
import pl.malek.automi.Entities.DrivingGearEntity;
import pl.malek.automi.Exceptions.DrivinGearCreationException;
import pl.malek.automi.Exceptions.DrivingGearNotFoundException;
import pl.malek.automi.Mappers.DrivingGearMapper;
import pl.malek.automi.Repository.DrivingGearRepository;
import pl.malek.automi.Service.DrivingGearService;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class DrivingGearServiceImpl implements DrivingGearService {

    private final DrivingGearRepository drivingGearRepository;
    private final DrivingGearMapper drivingGearMapper;

    @Override
    public DrivingGear add(DrivingGear drivingGear, BindingResult result) throws DrivinGearCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var drivingGearEntity = drivingGearRepository.save(drivingGearMapper.dtoToEntity(drivingGear));
        return drivingGearMapper.entityToDto(drivingGearEntity);
    }

    @Override
    public List<DrivingGear> getAll() {
        return drivingGearMapper.entitiesToDto(drivingGearRepository.findAll());
    }

    @Override
    public DrivingGear delete(long id) throws DrivingGearNotFoundException {
        var drivingGearEntity = getById(id);
        drivingGearRepository.deleteById(id);
        return drivingGearMapper.entityToDto(drivingGearEntity);
    }

    @Override
    public DrivingGear update(long id, DrivingGear drivingGear, BindingResult result) throws DrivinGearCreationException, DrivingGearNotFoundException {
        var drivingGearEntity = getById(id);
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        drivingGearEntity.setDrivingGearName(drivingGear.getDrivingGear());
        drivingGearRepository.save(drivingGearEntity);
        return drivingGearMapper.entityToDto(drivingGearEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws DrivinGearCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new DrivinGearCreationException(messages);
    }

    @Override
    public DrivingGearEntity getById(Long id) throws DrivingGearNotFoundException {
        return drivingGearRepository.findById(id).orElseThrow(
                () -> new DrivingGearNotFoundException(
                        String.format("Driving gear with id %d not found", id)
                )
        );
    }


}


