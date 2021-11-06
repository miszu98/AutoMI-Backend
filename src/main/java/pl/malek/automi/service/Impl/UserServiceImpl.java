package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
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
import java.util.Arrays;
import java.util.List;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final PasswordEncoder passwordEncoder;


    @Override
    public User add(User user, BindingResult result)
            throws UserCreationException, RoleNotFoundException {
        if (result.hasErrors()) {
            extractErrors(result.getAllErrors());
        }
        boolean userExist = userRepository.findByEmail(user.getEmail()).isPresent();
        if (userExist) {
            throw new UserCreationException(
                    Arrays.asList(
                                    String.format(
                                            "User with email: %s already exists",
                                            user.getEmail()
                                    )
                            )
            );
        }
        user.setRole("USER");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        var userEntity = userRepository.save(userMapper.dtoToEntity(user));
        return userMapper.entityToDto(userEntity);
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

    @Override
    public Long getIdByEmail(String email) {
        return userRepository.findByEmail(email).orElseThrow(
                () -> new UsernameNotFoundException(String.format("User with email %s: not found", email))
        ).getId();
    }
}
