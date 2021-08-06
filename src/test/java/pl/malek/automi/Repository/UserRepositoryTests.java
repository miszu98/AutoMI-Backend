package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.RoleEntity;
import pl.malek.automi.Entities.UserEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
public class UserRepositoryTests {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @BeforeEach
    void setUp() {
        RoleEntity role = RoleEntity
                .builder()
                .roleName("USER")
                .build();
        role = roleRepository.save(role);

        UserEntity user = UserEntity
                .builder()
                .email("marcin.nowak@wp.pl")
                .password("123")
                .roleEntity(role).build();
        userRepository.save(user);
    }

    @AfterEach
    void tearDown() {
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void shouldCheckIfUserExistWithId() {
        boolean userWithIdExist = userRepository.existsById(1L);
        assertTrue(userWithIdExist);
    }

    @Test
    void shouldReturnAllUsers() {
        List<UserEntity> users = userRepository.findAll();
        assertEquals(1, users.size());
    }
}
