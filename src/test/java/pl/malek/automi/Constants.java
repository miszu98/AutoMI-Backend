package pl.malek.automi;

import pl.malek.automi.DTO.Mark;
import pl.malek.automi.DTO.User;
import pl.malek.automi.Entities.MarkEntity;
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
                .roleEntity(RolesEntities.ROLE_USER_ENTITY)
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

    public static class MarksLabels {
        public static final String BMW = "BMW";
        public static final String AUDI = "AUDI";
        public static final String MERCEDES_BENZ = "Mercedes-Benz";
    }

    public static class Marks {
        public static final Mark BMW = Mark.builder()
                .id(1L)
                .mark(MarksLabels.BMW)
                .build();

        public static final Mark MERCEDES_BENZ = Mark.builder()
                .id(2L)
                .mark(MarksLabels.MERCEDES_BENZ)
                .build();

        public static final Mark AUDI = Mark.builder()
                .id(3L)
                .mark(MarksLabels.AUDI)
                .build();
    }

    public static class MarksEntities {
        public static final MarkEntity BMW_ENTITY = MarkEntity.builder()
                .id(1L)
                .mark(MarksLabels.BMW)
                .build();
    }



}
