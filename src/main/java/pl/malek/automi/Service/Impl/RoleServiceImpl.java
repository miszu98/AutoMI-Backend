package pl.malek.automi.Service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.Role;
import pl.malek.automi.Entities.RoleEntity;
import pl.malek.automi.Exceptions.RoleCreationException;
import pl.malek.automi.Exceptions.RoleNotFoundException;
import pl.malek.automi.Mappers.RoleMapper;
import pl.malek.automi.Repository.RoleRepository;
import pl.malek.automi.Service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public RoleEntity getByName(String roleName) throws RoleNotFoundException {
        return roleRepository.findByRoleName(roleName).orElseThrow(
                () -> new RoleNotFoundException(
                        "Role with name: " + roleName + " not found"
                )
        );
    }

    @Override
    public Role add(Role role, BindingResult result) throws RoleCreationException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var roleEntity = roleRepository.save(RoleMapper.dtoToEntity(role));
        return RoleMapper.entityToDto(roleEntity);
    }

    @Override
    public List<Role> getAll() {
        return RoleMapper.entitiesToDto(roleRepository.findAll());
    }

    @Override
    public Role delete(long id) throws RoleNotFoundException {
        var roleEntity = roleRepository.findById(id).orElseThrow(
                () -> new RoleNotFoundException(
                        "Role with id: " + id + " not found"
                )
        );
        roleRepository.deleteById(id);
        return RoleMapper.entityToDto(roleEntity);
    }

    @Override
    public Role update(long id, Role role, BindingResult result) throws RoleNotFoundException, RoleCreationException {
        var roleEntity = roleRepository.findById(id).orElseThrow(
                () -> new RoleNotFoundException(
                        "Role with id: " + id + " not found"
                )
        );
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        roleEntity.setRoleName(role.getRole());
        roleRepository.save(roleEntity);
        return RoleMapper.entityToDto(roleEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws RoleCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new RoleCreationException(messages);
    }
}
