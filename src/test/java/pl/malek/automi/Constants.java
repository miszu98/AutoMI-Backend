package pl.malek.automi;

import pl.malek.automi.DTO.*;
import pl.malek.automi.Entities.*;

/**
 * Class with inner classes which represent data
 * for tests for all objects:
 * {@link Users}
 * {@link Roles}
 * {@link Marks}
 * {@link Models}
 * {@link FuelTypes}
 * @author Michał Małek
 * @version 1.0
 */
public class Constants {

    /**
     * Class with Users data for tests
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

            public static final MarkEntity MERCEDES_BENZ = MarkEntity.builder()
                    .id(1L)
                    .mark(Labels.MERCEDES_BENZ)
                    .build();
        }

    }

    /**
     * Class with car Models data for tests
     */
    public static class Models {
        public static class Labels {
            public static final String M5 = "M5";
            public static final String C63S = "C63s";
            public static final String M5CS = "M5 CS";
        }

        public static class DataTransferObjects {
            public static final Model C63S = Model.builder()
                    .id(1L)
                    .model(Labels.C63S)
                    .markId(1L)
                    .build();

            public static final Model M5 = Model.builder()
                    .id(2L)
                    .model(Labels.M5)
                    .markId(2L)
                    .build();
        }

        public static class Entities {
            public static final ModelEntity C63S = ModelEntity.builder()
                    .id(1L)
                    .model(Labels.C63S)
                    .markEntity(Marks.Entities.MERCEDES_BENZ)
                    .build();

            public static final ModelEntity M5 = ModelEntity.builder()
                    .id(2L)
                    .model(Labels.M5)
                    .markEntity(Marks.Entities.BMW_ENTITY)
                    .build();
        }
    }

    public static class FuelTypes {
        public static class Labels {
            public static final String GAS = "GAS";
            public static final String DIESEL = "DIESEL";
        }
        public static class DataTransferObjects {
            public static final FuelType GAS = FuelType.builder()
                    .id(1L)
                    .fuelTypeName(Labels.GAS)
                    .build();

            public static final FuelType DIESEL = FuelType.builder()
                    .id(2L)
                    .fuelTypeName(Labels.DIESEL)
                    .build();
        }

        public static class Entities {
            public static final FuelTypeEntity DIESEL = FuelTypeEntity.builder()
                    .id(2L)
                    .fuelTypeName(Labels.DIESEL)
                    .build();
        }
    }

    public static class Gearboxes {
        public static class Labels {
            public static final String AUTOMATIC = "AUTOMATIC";
            public static final String MANUAL = "MANUAL";
        }

        public static class DataTransferObjects {
            public static final pl.malek.automi.DTO.Gearbox AUTOMATIC = Gearbox.builder()
                    .id(1L)
                    .gearbox(Labels.AUTOMATIC)
                    .build();
            public static final pl.malek.automi.DTO.Gearbox MANUAL = Gearbox.builder()
                    .id(2L)
                    .gearbox(Labels.MANUAL)
                    .build();
        }
    }
}




