package pl.malek.automi.Service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.validation.BindingResult;
import pl.malek.automi.DTO.Role;
import pl.malek.automi.Entities.RoleEntity;
import pl.malek.automi.Exceptions.RoleCreationException;
import pl.malek.automi.Mappers.RoleMapper;
import pl.malek.automi.Repository.RoleRepository;
import pl.malek.automi.Service.Impl.RoleServiceImpl;
import java.util.List;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class RoleServiceTests {

    @Mock
    private RoleRepository roleRepository;

    @Mock
    private RoleMapper roleMapper;

    @InjectMocks
    private RoleServiceImpl roleService;

    @Mock
    private BindingResult bindingResult;

    @Test
    void checkIfMocksWorks() {
        assertNotNull(roleRepository);
        assertNotNull(roleService);
    }

    @Test
    void shouldReturnsListOfRoles() {
        Role user = Role.builder().role("USER").build();
        Role admin = Role.builder().role("ADMIN").build();
        var roles = List.of(user, admin);

        given(roleService.getAll()).willReturn(roles);

        var response = roleService.getAll();

        assertEquals(2, response.size());
        assertEquals("USER", response.get(0).getRole());
    }

    @Test
    void shouldAddRole() throws RoleCreationException {
        Role role = Role.builder().role("MODERATOR").build();

        roleService.add(role, bindingResult);

        ArgumentCaptor<RoleEntity> roleArgumentCaptor = ArgumentCaptor.forClass(RoleEntity.class);
        verify(roleRepository).save(roleArgumentCaptor.capture());

        assertThat(roleMapper.dtoToEntity(role)).isEqualTo(roleArgumentCaptor.getValue());
    }

    @Test
    void shouldUpdateGivenRole() {

    }
}
