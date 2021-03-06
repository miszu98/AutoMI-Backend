package pl.malek.automi.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.dto.User;
import pl.malek.automi.entity.UserEntity;
import pl.malek.automi.exception.RoleNotFoundException;
import pl.malek.automi.service.RoleService;

import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class UserMapper {

    private final RoleService roleService;

    public UserEntity dtoToEntity(User user) throws RoleNotFoundException {
        return UserEntity
                .builder()
                .id(user.getId())
                .email(user.getEmail())
                .password(user.getPassword())
                .isActive(true)
                .roleEntity(roleService.getByName(user.getRole()))
                .build();
    }

    public User entityToDto(UserEntity userEntity) {
        return User
                .builder()
                .id(userEntity.getId())
                .email(userEntity.getEmail())
                .dateJoined(userEntity.getDateJoined())
                .isActive(userEntity.getIsActive())
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
                                .isActive(user.getIsActive())
                                .dateJoined(user.getDateJoined())
                                .role(user.getRoleEntity().getRoleName())
                                .build()
                ).collect(Collectors.toList());
    }
}
