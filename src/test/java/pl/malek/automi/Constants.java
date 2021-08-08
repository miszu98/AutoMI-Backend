package pl.malek.automi;

import pl.malek.automi.DTO.User;
import pl.malek.automi.Entities.RoleEntity;
import pl.malek.automi.Entities.UserEntity;

public class Constants {

    public static class Users {
        public static final User USER = User.builder()
                .email("r.nowak@automi.com")
                .password("Jakiestam1.")
                .role(Roles.ROLE_USER)
                .build();

        public static final User ADMIN = User.builder()
                .email("admin@automi.com")
                .password("Jakiestam1.")
                .role(Roles.ROLE_ADMIN)
                .build();

        public static final User UPDATED_USER = User.builder()
                .email("r.nowak@automi-office.com")
                .password("Jakiestam1.")
                .role(Roles.ROLE_USER)
                .build();
    }

    public static class UsersEntities {
        public static final UserEntity USER_ENTITY = UserEntity.builder()
                .id(1L)
                .email("r.nowak@automi.com")
                .password("Jakiestam1.")
                .roleEntity(RoleEntity.builder().roleName(Roles.ROLE_USER).build())
                .build();
    }

    public static class Roles {
        public static final String ROLE_USER = "USER";
        public static final String ROLE_ADMIN = "ADMIN";
    }

    public static class RolesEntities {
        public static final RoleEntity ROLE_USER_ENTITY = RoleEntity.builder()
                .roleName(Roles.ROLE_USER).build();
    }



}
