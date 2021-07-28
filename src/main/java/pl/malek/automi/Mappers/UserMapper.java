package pl.malek.automi.Mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.DTO.User;
import pl.malek.automi.Entities.UserEntity;
import pl.malek.automi.Exceptions.RoleNotFoundException;
import pl.malek.automi.Service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private final RoleService roleService;

    public UserEntity dtoToEntity(User user) throws RoleNotFoundException {
        return UserEntity
                .builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .isActive(true)
                .roleEntity(roleService.getByName(user.getRole()))
                .build();
    }

    public User entityToDto(UserEntity userEntity) {
        return User
                .builder()
                .email(userEntity.getEmail())
                .password(userEntity.getPassword())
                .role(userEntity.getRoleEntity().getRoleName())
                .build();
    }

    public List<User> entitiesToDto(List<UserEntity> userEntities) {
        return userEntities
                .stream()
                .map(user -> User
                                .builder()
                                .id(user.getId())
                                .email(user.getEmail())
                                .password(user.getPassword())
                                .role(user.getRoleEntity().getRoleName())
                                .build()
                ).collect(Collectors.toList());
    }
}
