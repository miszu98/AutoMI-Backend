package pl.malek.automi.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.FuelType;
import pl.malek.automi.Entities.FuelTypeEntity;
import pl.malek.automi.Exceptions.FuelTypeCreationException;
import pl.malek.automi.Exceptions.FuelTypeNotFoundException;
import pl.malek.automi.Mappers.FuelTypeMapper;
import pl.malek.automi.Repository.FuelTypeRepository;
import pl.malek.automi.Service.FuelTypeService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class FuelTypeServiceImpl implements FuelTypeService {

    private final FuelTypeRepository fuelTypeRepository;

    @Override
    public FuelType add(FuelType fuelType, BindingResult result) throws FuelTypeCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var fuelTypeEntity = fuelTypeRepository.save(FuelTypeMapper.dtoToEntity(fuelType));
        return FuelTypeMapper.entityToDto(fuelTypeEntity);
    }

    @Override
    public List<FuelType> getAll() {
        return FuelTypeMapper.entitiesToDto(fuelTypeRepository.findAll());
    }

    @Override
    public FuelType delete(long id) throws FuelTypeNotFoundException {
        var fuelTypeEntity = getById(id);
        fuelTypeRepository.deleteById(id);
        return FuelTypeMapper.entityToDto(fuelTypeEntity);
    }

    @Override
    public FuelType update(long id, FuelType fuelType, BindingResult result) throws FuelTypeCreationException, FuelTypeNotFoundException {
        var fuelTypeEntity = getById(id);
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        fuelTypeEntity.setFuelTypeName(fuelType.getFuelTypeName());
        fuelTypeRepository.save(fuelTypeEntity);
        return FuelTypeMapper.entityToDto(fuelTypeEntity);
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
