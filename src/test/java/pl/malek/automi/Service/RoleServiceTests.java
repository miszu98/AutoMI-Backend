package pl.malek.automi.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.UtilsMI.Constants;
import pl.malek.automi.DTO.Role;
import pl.malek.automi.Entities.RoleEntity;
import pl.malek.automi.Exceptions.RoleCreationException;
import pl.malek.automi.Exceptions.RoleNotFoundException;
import pl.malek.automi.Mappers.RoleMapper;
import pl.malek.automi.Repository.RoleRepository;
import pl.malek.automi.Service.Impl.RoleServiceImpl;
import java.util.List;
import java.util.Optional;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTests {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleMapper roleMapper;

    @Mock
    private BindingResult bindingResult;

    @InjectMocks
    private RoleServiceImpl roleServiceImpl;

    @Test
    void checkIfMocksWorks() {
        assertNotNull(roleRepository);
        assertNotNull(roleServiceImpl);
    }

    @Test
    void shouldReturnsListOfRoles() {
        var user = Role.builder().role(Constants.Roles.Labels.ROLE_USER).build();
        var admin = Role.builder().role(Constants.Roles.Labels.ROLE_ADMIN).build();
        var roles = List.of(user, admin);

        given(roleServiceImpl.getAll()).willReturn(roles);

        verify(roleRepository).findAll();

        assertEquals(2, roleServiceImpl.getAll().size());
        assertEquals(Constants.Roles.Labels.ROLE_USER, roleServiceImpl.getAll().get(0).getRole());
    }

    @Test
    void shouldAddRole() throws RoleCreationException {
        var role = Role.builder().role("MODERATOR").build();

        roleServiceImpl.add(role, bindingResult);

        var roleArgumentCaptor = ArgumentCaptor.forClass(RoleEntity.class);
        verify(roleRepository).save(roleArgumentCaptor.capture());

        assertThat(roleMapper.dtoToEntity(role)).isEqualTo(roleArgumentCaptor.getValue());
    }

    @Test
    void shouldUpdateGivenRole() throws RoleNotFoundException, RoleCreationException {
        var id = 1L;
        var old = RoleEntity.builder().roleName(Constants.Roles.Labels.ROLE_USER).build();
        var updated = Role.builder().role("NEW_USER").build();

        given(roleRepository.findById(id)).willReturn(Optional.of(old));

        roleServiceImpl.update(id, updated, bindingResult);

        var roleArgumentCaptor = ArgumentCaptor.forClass(RoleEntity.class);

        verify(roleRepository).findById(id);
        verify(roleRepository).save(roleArgumentCaptor.capture());

        assertEquals("NEW_USER", roleArgumentCaptor.getValue().getRoleName());
        assertNotEquals(Constants.Roles.Labels.ROLE_USER, roleArgumentCaptor.getValue().getRoleName());
    }

    @Test
    void shouldDeleteRole() throws RoleNotFoundException {
        var id = 1L;
        var role = RoleEntity.builder().roleName(Constants.Roles.Labels.ROLE_ADMIN).build();

        given(roleRepository.findById(id)).willReturn(Optional.of(role));

        roleServiceImpl.delete(id);

        verify(roleRepository).findById(id);
        verify(roleRepository).deleteById(id);

    }

}
