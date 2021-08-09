package pl.malek.automi.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.*;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.validation.BindingResult;
import pl.malek.automi.DTO.User;
import pl.malek.automi.Entities.RoleEntity;
import pl.malek.automi.Entities.UserEntity;
import pl.malek.automi.Repository.RoleRepository;
import pl.malek.automi.Repository.UserRepository;
import pl.malek.automi.Service.Impl.RoleServiceImpl;
import pl.malek.automi.Service.Impl.UserServiceImpl;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTests {

    @Autowired private MockMvc mvc;

    @Autowired private ObjectMapper objectMapper;

    @Mock private UserRepository userRepository;

    @InjectMocks private UserServiceImpl userService;

    @Mock private RoleRepository roleRepository;

    @InjectMocks private RoleServiceImpl roleService;

    @Mock private BindingResult bindingResult;



    @Test
    void should_return_status_ok_while_try_to_get_all_users() throws Exception {
        mvc.perform(get("/users/"))
                .andExpect(status().isOk());
    }

    @Test
    void should_return_status_conflict_while_try_to_delete_not_exist_user() throws Exception {
        mvc.perform(delete("/users/199"))
                .andExpect(status().isConflict());
    }

    @Test
    void should_return_status_conflict_while_try_to_update_not_exist_user() throws Exception {
        User user = User
                .builder()
                .email("michal.malek98@wp.pl")
                .password("Jakiestam1.")
                .role("ADMIN").build();
        mvc.perform(put("/users/250")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isConflict());
    }

    @Test
    void should_add_user_to_db() throws Exception {
        when(roleService.getByName("ADMIN")).thenReturn(RoleEntity.builder().id(1L).roleName("ADMIN").build());

        UserEntity user = UserEntity.builder().email("michal.malek98@wp.pl").password("Jakiestam1.").roleEntity(roleService.getByName("ADMIN")).build();

        mvc.perform(post("/users/").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk());
    }


}
