package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.Role;
import pl.malek.automi.entity.RoleEntity;
import pl.malek.automi.exception.RoleCreationException;
import pl.malek.automi.exception.RoleNotFoundException;
import pl.malek.automi.mapper.RoleMapper;
import pl.malek.automi.repository.RoleRepository;
import pl.malek.automi.service.RoleService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;
    private final RoleMapper roleMapper;

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
        var roleEntity = roleRepository.save(roleMapper.dtoToEntity(role));
        return roleMapper.entityToDto(roleEntity);
    }

    @Override
    public List<Role> getAll() {
        return roleMapper.entitiesToDto(roleRepository.findAll());
    }

    @Override
    public Role delete(long id) throws RoleNotFoundException {
        var roleEntity = getById(id);
        roleRepository.deleteById(id);
        return roleMapper.entityToDto(roleEntity);
    }

    @Override
    public Role update(long id, Role role, BindingResult result) throws RoleNotFoundException, RoleCreationException {
        var roleEntity = getById(id);
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        roleEntity.setRoleName(role.getRole());
        roleRepository.save(roleEntity);
        return roleMapper.entityToDto(roleEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws RoleCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new RoleCreationException(messages);
    }

    @Override
    public RoleEntity getById(Long id) throws RoleNotFoundException {
        return roleRepository.findById(id).orElseThrow(
                () -> new RoleNotFoundException(
                        "Role with id: " + id + " not found"
                )
        );
    }

}
