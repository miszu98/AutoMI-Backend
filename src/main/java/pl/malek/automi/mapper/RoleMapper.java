package pl.malek.automi.mapper;


import org.springframework.stereotype.Component;
import pl.malek.automi.dto.Role;
import pl.malek.automi.entity.RoleEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class RoleMapper {

    public RoleEntity dtoToEntity(Role role) {
        return RoleEntity
                .builder()
                .roleName(role.getRole())
                .build();
    }

    public List<Role> entitiesToDto(List<RoleEntity> roleEntities) {
        return roleEntities
                .stream()
                .map(role -> Role
                        .builder()
                        .id(role.getId())
                        .role(role.getRoleName())
                        .build()
                ).collect(Collectors.toList());
    }

    public Role entityToDto(RoleEntity roleEntity) {
        return Role
                .builder()
                .id(roleEntity.getId())
                .role(roleEntity.getRoleName())
                .build();
    }
}
