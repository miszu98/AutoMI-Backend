package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.RoleEntity;
import pl.malek.automi.GenericTest;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        var userRole = RoleEntity
                                    .builder()
                                    .roleName("USER")
                                    .build();
        var adminRole = RoleEntity
                                    .builder()
                                    .roleName("ADMIN")
                                    .build();
        roleRepository.saveAll(List.of(userRole, adminRole));
    }

    @AfterEach
    void tearDown() {
        roleRepository.deleteAll();
    }

    @Test
    void shouldCheckIfRolesNotExists() {
        boolean unknownRoleA = roleRepository.existsById(5L);
        boolean unknownRoleB = roleRepository.existsById(1253L);

        assertFalse(unknownRoleA);
        assertFalse(unknownRoleB);
    }

    @Test
    void shouldCheckIfAdminRoleExist() {
        var roleEntity = GenericTest.tryToGetEntityObject(roleRepository.findByRoleName("ADMIN"));
        assertEquals("ADMIN", roleEntity.getRoleName());
    }

    @Test
    void shouldCheckIfUserRoleExist() {
        var roleEntity = GenericTest.tryToGetEntityObject(roleRepository.findByRoleName("USER"));
        assertEquals("USER", roleEntity.getRoleName());
    }

    @Test
    void shouldAddRole() {
        var moderatorRole = RoleEntity
                                        .builder()
                                        .roleName("MODERATOR")
                                        .build();
        roleRepository.save(moderatorRole);

        var roles = roleRepository.findAll();

        assertEquals(3, roles.size());
    }

}
