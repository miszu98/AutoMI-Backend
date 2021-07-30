package pl.malek.automi.Mappers;


import pl.malek.automi.DTO.Role;
import pl.malek.automi.Entities.RoleEntity;

import java.util.List;
import java.util.stream.Collectors;

public class RoleMapper {

    public static RoleEntity dtoToEntity(Role role) {
        return RoleEntity
                .builder()
                .roleName(role.getRole())
                .build();
    }

    public static List<Role> entitiesToDto(List<RoleEntity> roleEntities) {
        return roleEntities
                .stream()
                .map(role -> Role
                        .builder()
                        .id(role.getId())
                        .role(role.getRoleName())
                        .build()
                ).collect(Collectors.toList());
    }

    public static Role entityToDto(RoleEntity roleEntity) {
        return Role
                .builder()
                .id(roleEntity.getId())
                .role(roleEntity.getRoleName())
                .build();
    }
}
