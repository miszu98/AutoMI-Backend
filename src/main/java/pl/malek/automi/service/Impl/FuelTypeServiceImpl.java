package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.FuelType;
import pl.malek.automi.entity.FuelTypeEntity;
import pl.malek.automi.exception.FuelTypeCreationException;
import pl.malek.automi.exception.FuelTypeNotFoundException;
import pl.malek.automi.mapper.FuelTypeMapper;
import pl.malek.automi.repository.FuelTypeRepository;
import pl.malek.automi.service.FuelTypeService;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FuelTypeServiceImpl implements FuelTypeService {

    private final FuelTypeRepository fuelTypeRepository;
    private final FuelTypeMapper fuelTypeMapper;

    @Override
    public FuelType add(FuelType fuelType, BindingResult result) throws FuelTypeCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var fuelTypeEntity = fuelTypeRepository.save(fuelTypeMapper.dtoToEntity(fuelType));
        return fuelTypeMapper.entityToDto(fuelTypeEntity);
    }

    @Override
    public List<FuelType> getAll() {
        return fuelTypeMapper.entitiesToDto(fuelTypeRepository.findAll());
    }

    @Override
    public FuelType delete(long id) throws FuelTypeNotFoundException {
        var fuelTypeEntity = getById(id);
        fuelTypeRepository.deleteById(id);
        return fuelTypeMapper.entityToDto(fuelTypeEntity);
    }

    @Override
    public FuelType update(long id, FuelType fuelType, BindingResult result) throws FuelTypeCreationException, FuelTypeNotFoundException {
        var fuelTypeEntity = getById(id);
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        fuelTypeEntity.setFuelTypeName(fuelType.getFuelTypeName());
        fuelTypeRepository.save(fuelTypeEntity);
        return fuelTypeMapper.entityToDto(fuelTypeEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws FuelTypeCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new FuelTypeCreationException(messages);
    }

    @Override
    public FuelTypeEntity getById(Long id) throws FuelTypeNotFoundException {
        return fuelTypeRepository.findById(id).orElseThrow(
                () -> new FuelTypeNotFoundException(
                        String.format("Fuel type with id %d not found", id)
                )
        );
    }
}
