package pl.malek.automi.Service.Impl;

import lombok.AllArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import pl.malek.automi.DTO.User;
import pl.malek.automi.Exceptions.RoleNotFoundException;
import pl.malek.automi.Exceptions.UserCreationException;
import pl.malek.automi.Exceptions.UserNotFoundException;
import pl.malek.automi.Mappers.UserMapper;
import pl.malek.automi.Repository.UserRepository;
import pl.malek.automi.Service.RoleService;
import pl.malek.automi.Service.UserService;

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
        var userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        "User with id: " + id + " not found"
                )
        );
        userRepository.deleteById(id);
        return userMapper.entityToDto(userEntity);
    }

    @Override
    public User update(long id, User user, BindingResult result) throws UserNotFoundException, UserCreationException, RoleNotFoundException {
        var userEntity = userRepository.findById(id).orElseThrow(
                () -> new UserNotFoundException(
                        "User with id: " + id + " not found"
                )
        );
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
}
