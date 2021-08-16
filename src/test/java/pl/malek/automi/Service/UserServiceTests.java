package pl.malek.automi.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.UtilsMI.Constants;
import pl.malek.automi.Entities.UserEntity;
import pl.malek.automi.Exceptions.RoleNotFoundException;
import pl.malek.automi.Exceptions.UserCreationException;
import pl.malek.automi.Exceptions.UserNotFoundException;
import pl.malek.automi.Mappers.UserMapper;
import pl.malek.automi.Repository.RoleRepository;
import pl.malek.automi.Repository.UserRepository;
import pl.malek.automi.Service.Impl.RoleServiceImpl;
import pl.malek.automi.Service.Impl.UserServiceImpl;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock private UserRepository userRepository;
    @Mock private UserMapper userMapper;
    @Mock private BindingResult bindingResult;
    @Mock private RoleRepository roleRepository;
    @Mock private RoleServiceImpl roleService;
    @InjectMocks private UserServiceImpl userService;

    @Test
    void checkIfMocksWorks() {
        assertNotNull(roleRepository);
        assertNotNull(userRepository);
        assertNotNull(userMapper);
        assertNotNull(bindingResult);
        assertNotNull(userService);
        assertNotNull(roleService);
    }

    @Test
    void shouldGetAllUsers() {
        var user = Constants.Users.DataTransferObjects.USER;
        var admin = Constants.Users.DataTransferObjects.ADMIN;
        var users = List.of(user, admin);

        given(userService.getAll()).willReturn(users);

        verify(userRepository).findAll();

        assertEquals(2, userService.getAll().size());
    }

    @Test
    void shouldAddUser() throws RoleNotFoundException, UserCreationException {
        var user = Constants.Users.DataTransferObjects.USER;

        given(userMapper.dtoToEntity(user))
                .willReturn(
                        UserEntity.builder()
                                .email("r.nowak@automi.com")
                                .password("Jakiestam1.")
                                .roleEntity(Constants.Roles.Entities.ROLE_USER_ENTITY).build());

        userService.add(user, bindingResult);

        var userArgumentCaptor= ArgumentCaptor.forClass(UserEntity.class);

        verify(userMapper).dtoToEntity(user);
        verify(userRepository).save(userArgumentCaptor.capture());

        assertThat(userMapper.dtoToEntity(user)).isEqualTo(userArgumentCaptor.getValue());
        assertEquals("r.nowak@automi.com", userArgumentCaptor.getValue().getEmail());
    }

    @Test
    void shouldUpdateUser() throws UserNotFoundException, RoleNotFoundException, UserCreationException {
        var id = 1L;
        var oldUser = Constants.Users.Entities.USER_ENTITY;
        var updatedUser = Constants.Users.DataTransferObjects.UPDATED_USER;

        given(roleService.getByName(Constants.Roles.Labels.ROLE_USER)).willReturn(Constants.Roles.Entities.ROLE_USER_ENTITY);
        given(userRepository.findById(id))
                .willReturn(Optional.of(oldUser));

        userService.update(id, updatedUser, bindingResult);

        var userArgumentCaptor = ArgumentCaptor.forClass(UserEntity.class);

        verify(userRepository).save(userArgumentCaptor.capture());

        assertThat(oldUser).isEqualTo(userArgumentCaptor.getValue());
        assertEquals("r.nowak@automi-office.com", userArgumentCaptor.getValue().getEmail());
    }

    @Test
    void shouldDeleteUser() throws UserNotFoundException {
        var id = 1L;

        given(userRepository.findById(id)).willReturn(Optional.of(Constants.Users.Entities.USER_ENTITY));

        userService.delete(id);

        verify(userRepository).findById(id);
        verify(userRepository).deleteById(id);

        assertEquals(1, userService.getById(1L).getId());
    }
}
