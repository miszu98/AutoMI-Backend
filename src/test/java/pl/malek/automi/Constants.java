package pl.malek.automi;

import pl.malek.automi.DTO.Mark;
import pl.malek.automi.DTO.User;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.Entities.RoleEntity;
import pl.malek.automi.Entities.UserEntity;

/**
 * Class with inner classes which represent data
 * for tests for all objects:
 * {@link Users}
 * {@link Roles}
 * {@link Marks}
 * @author Michał Małek
 * @version 1.0
 */
public class Constants {

    /**
     * Class with Users data for tests
     * {@link DataTransferObjects}
     * {@link Entities}
     * @author Michał Małek
     * @version 1.0
     */
    public static class Users {

        public static class DataTransferObjects {
            public static final User USER = User.builder()
                    .email("r.nowak@automi.com")
                    .password("Jakiestam1.")
                    .role(Roles.Labels.ROLE_USER)
                    .build();

            public static final User ADMIN = User.builder()
                    .email("admin@automi.com")
                    .password("Jakiestam1.")
                    .role(Roles.Labels.ROLE_ADMIN)
                    .build();

            public static final User UPDATED_USER = User.builder()
                    .email("r.nowak@automi-office.com")
                    .password("Jakiestam1.")
                    .role(Roles.Labels.ROLE_USER)
                    .build();
        }

        public static class Entities {
            public static final UserEntity USER_ENTITY = UserEntity.builder()
                    .id(1L)
                    .email("r.nowak@automi.com")
                    .password("Jakiestam1.")
                    .roleEntity(Roles.Entities.ROLE_USER_ENTITY)
                    .build();
        }
    }

    /**
     * Class with Roles data for tests
     * {@link Labels}
     * {@link Entities}
     * @author Michał Małek
     * @version 1.0
     */
    public static class Roles {

        public static class Labels {
            public static final String ROLE_USER = "USER";
            public static final String ROLE_ADMIN = "ADMIN";
        }

        public static class Entities {
            public static final RoleEntity ROLE_USER_ENTITY = RoleEntity.builder()
                    .roleName(Labels.ROLE_USER).build();
        }

    }

    /**
     * Class with car Marks data for tests
     * {@link Labels}
     * {@link Entities}
     * {@link DataTransferObjects}
     * @author Michał Małek
     * @version 1.0
     */
    public static class Marks {

        public static class Labels {
            public static final String BMW = "BMW";
            public static final String AUDI = "AUDI";
            public static final String MERCEDES_BENZ = "Mercedes-Benz";
        }

        public static class DataTransferObjects {
            public static final Mark BMW = Mark.builder()
                    .id(1L)
                    .mark(Labels.BMW)
                    .build();

            public static final Mark MERCEDES_BENZ = Mark.builder()
                    .id(2L)
                    .mark(Labels.MERCEDES_BENZ)
                    .build();

            public static final Mark AUDI = Mark.builder()
                    .id(3L)
                    .mark(Labels.AUDI)
                    .build();
        }

        public static class Entities {
            public static final MarkEntity BMW_ENTITY = MarkEntity.builder()
                    .id(1L)
                    .mark(Labels.BMW)
                    .build();
        }

    }
}




