package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.dto.User;
import pl.malek.automi.entity.UserEntity;
import pl.malek.automi.exception.RoleNotFoundException;
import pl.malek.automi.exception.UserCreationException;
import pl.malek.automi.exception.UserNotFoundException;
import pl.malek.automi.mapper.UserMapper;
import pl.malek.automi.repository.UserRepository;
import pl.malek.automi.service.RoleService;
import pl.malek.automi.service.UserService;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;


    @Override
    public User add(User user, BindingResult result) throws UserCreationException, RoleNotFoundException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        var userEntity = userRepository.save(userMapper.dtoToEntity(user));
        return userMapper.entityToDto(userEntity);
    }

    @Override
    public List<User> getAll() {
        return userMapper.entitiesToDto(userRepository.findAll());
    }

    @Override
    public User delete(long id) throws UserNotFoundException {
        var userEntity = getById(id);
        userRepository.deleteById(id);
        return userMapper.entityToDto(userEntity);
    }

    @Override
    public User update(long id, User user, BindingResult result) throws UserNotFoundException, UserCreationException, RoleNotFoundException {
        var userEntity = getById(id);
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        userEntity.setEmail(user.getEmail());
        userEntity.setPassword(user.getPassword());
        userEntity.setRoleEntity(roleService.getByName(user.getRole()));
        userRepository.save(userEntity);
        return userMapper.entityToDto(userEntity);
    }

    @Override
    public void extractErrors(List<ObjectError> errors) throws UserCreationException {
        var messages = new ArrayList<String>();
        for (ObjectError error : errors) {
            messages.add(error.getDefaultMessage());
        }
        throw new UserCreationException(messages);
    }

    @Override
    public UserEntity getById(long id) throws UserNotFoundException {
        return userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        String.format("User with id: %d not found", id)
                )
        );
    }
}
