package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.RoleEntity;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class RoleRepositoryTests {

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        RoleEntity userRole = RoleEntity
                                    .builder()
                                    .roleName("USER")
                                    .build();
        RoleEntity adminRole = RoleEntity
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
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRoleName("ADMIN");
        boolean isExist = optionalRoleEntity.isPresent();
        RoleEntity roleAdmin;
        if (isExist) {
            roleAdmin = optionalRoleEntity.get();
        } else {
            roleAdmin = new RoleEntity();
        }
        assertEquals("ADMIN", roleAdmin.getRoleName());
    }

    @Test
    void shouldCheckIfUserRoleExist() {
        Optional<RoleEntity> optionalRoleEntity = roleRepository.findByRoleName("USER");
        boolean isExist = optionalRoleEntity.isPresent();
        RoleEntity roleUser;
        if (isExist) {
            roleUser = optionalRoleEntity.get();
        } else {
            roleUser = new RoleEntity();
        }
        assertEquals("USER", roleUser.getRoleName());
    }

    @Test
    void shouldAddRole() {
        RoleEntity moderatorRole = RoleEntity
                                        .builder()
                                        .roleName("MODERATOR")
                                        .build();
        roleRepository.save(moderatorRole);

        List<RoleEntity> roles = roleRepository.findAll();

        assertEquals(3, roles.size());
    }

}
