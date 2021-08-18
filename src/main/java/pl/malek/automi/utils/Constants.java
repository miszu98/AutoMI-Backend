package pl.malek.automi.utils;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import pl.malek.automi.dto.*;
import pl.malek.automi.entity.*;
import pl.malek.automi.enums.CarType;
import pl.malek.automi.enums.State;

import java.time.LocalDate;

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
@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Constants {

    /**
     * Class with Users data for tests
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Users {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        private static class Passwords {
            private static final String PASSWORD = "Jakiestam1.";
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        private static class Emails {
            private static final String EMAIL_A = "r.nowak@automi.com";
            private static final String EMAIL_B = "admin@automi.com";
            private static final String EMAIL_C = "r.nowak@automi-office.com";
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class DataTransferObjects {
            public static final User USER = User.builder()
                    .email(Emails.EMAIL_A)
                    .password(Passwords.PASSWORD)
                    .role(Roles.Labels.ROLE_USER)
                    .build();

            public static final User ADMIN = User.builder()
                    .email(Emails.EMAIL_B)
                    .password(Passwords.PASSWORD)
                    .role(Roles.Labels.ROLE_ADMIN)
                    .build();

            public static final User UPDATED_USER = User.builder()
                    .email(Emails.EMAIL_C)
                    .password(Passwords.PASSWORD)
                    .role(Roles.Labels.ROLE_USER)
                    .build();
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Entities {
            public static final UserEntity USER_ENTITY = UserEntity.builder()
                    .id(1L)
                    .email(Emails.EMAIL_A)
                    .password(Passwords.PASSWORD)
                    .roleEntity(Roles.Entities.ROLE_USER_ENTITY)
                    .build();
        }
    }

    /**
     * Class with Roles data for tests
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Roles {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Labels {
            public static final String ROLE_USER = "USER";
            public static final String ROLE_ADMIN = "ADMIN";
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Entities {
            public static final RoleEntity ROLE_USER_ENTITY = RoleEntity.builder()
                    .roleName(Labels.ROLE_USER).build();
        }

    }

    /**
     * Class with car Marks data for tests
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Marks {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Labels {
            public static final String BMW = "BMW";
            public static final String AUDI = "AUDI";
            public static final String MERCEDES_BENZ = "Mercedes-Benz";
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
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

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
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
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Models {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Labels {
            public static final String M5 = "M5";
            public static final String C63S = "C63s";
            public static final String M5CS = "M5 CS";
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
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

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
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

    /**
     * Class with car Fuel types data for tests
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class FuelTypes {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Labels {
            public static final String GAS = "GAS";
            public static final String DIESEL = "DIESEL";
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
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

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Entities {
            public static final FuelTypeEntity GAS = FuelTypeEntity.builder()
                    .id(1L)
                    .fuelTypeName(Labels.GAS)
                    .build();
            public static final FuelTypeEntity DIESEL = FuelTypeEntity.builder()
                    .id(2L)
                    .fuelTypeName(Labels.DIESEL)
                    .build();
        }
    }

    /**
     * Class with car Gearboxes data for tests
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Gearboxes {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Labels {
            public static final String AUTOMATIC = "AUTOMATIC";
            public static final String MANUAL = "MANUAL";
            public static final String TIPTRONIC = "TIPTRONIC";
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class DataTransferObjects {
            public static final Gearbox AUTOMATIC = Gearbox.builder()
                    .id(1L)
                    .gearbox(Labels.AUTOMATIC)
                    .build();
            public static final Gearbox MANUAL = Gearbox.builder()
                    .id(2L)
                    .gearbox(Labels.MANUAL)
                    .build();
            public static final Gearbox TIPTRONIC = Gearbox.builder()
                    .id(3L)
                    .gearbox(Labels.TIPTRONIC)
                    .build();
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Entities {
            public static final GearboxEntity AUTOMATIC = GearboxEntity.builder()
                    .id(1L)
                    .gearbox(Labels.AUTOMATIC)
                    .build();
            public static final GearboxEntity MANUAL = GearboxEntity.builder()
                    .id(2L)
                    .gearbox(Labels.MANUAL)
                    .build();
            public static final GearboxEntity TIPTRONIC = GearboxEntity.builder()
                    .id(3L)
                    .gearbox(Labels.TIPTRONIC)
                    .build();
        }
    }

    /**
     * Class with car colors data for tests
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Colors {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Labels {
            public static final String BLACK = "BLACK";
            public static final String GREY = "GREY";
            public static final String RED = "RED";
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class DataTransferObjects {
            public static final Color BLACK = Color.builder()
                    .id(1L)
                    .colorName(Labels.BLACK)
                    .build();
            public static final Color GREY = Color.builder()
                    .id(2L)
                    .colorName(Labels.GREY)
                    .build();
            public static final Color RED = Color.builder()
                    .id(3L)
                    .colorName(Labels.RED)
                    .build();
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Entities {
            public static final ColorEntity BLACK = ColorEntity.builder()
                    .id(1L)
                    .colorName(Labels.BLACK)
                    .build();
            public static final ColorEntity RED = ColorEntity.builder()
                    .id(3L)
                    .colorName(Labels.RED)
                    .build();
        }
    }

    /**
     * Class with Driving gears data for tests
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class DrivingGears {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class DataTransferObjects {
            public static final DrivingGear RWD = DrivingGear.builder()
                    .id(1L)
                    .drivingGear(Labels.RWD)
                    .build();
            public static final DrivingGear FWD = DrivingGear.builder()
                    .id(2L)
                    .drivingGear(Labels.FWD)
                    .build();
            public static final DrivingGear AWD = DrivingGear.builder()
                    .id(3L)
                    .drivingGear(Labels.AWD)
                    .build();
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Labels {
            public static final String RWD = "RWD";
            public static final String FWD = "FWD";
            public static final String AWD = "AWD";
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Entities {
            public static final DrivingGearEntity AWD = DrivingGearEntity.builder()
                    .id(3L)
                    .drivingGear(Labels.AWD)
                    .build();
        }
    }

    /**
     * Class with car Cars data for tests
     */
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class Cars {

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class DataTransferObjects {
            public static final Car BMW = Car.builder()
                    .id(1L)
                    .mark(Marks.DataTransferObjects.BMW)
                    .model(Models.DataTransferObjects.M5)
                    .fuelType(FuelTypes.DataTransferObjects.GAS)
                    .power(550L)
                    .engineCapacity(4300L)
                    .gearbox(Gearboxes.DataTransferObjects.AUTOMATIC)
                    .mileage(0L)
                    .drivingGear(DrivingGears.DataTransferObjects.RWD)
                    .state(State.BRAND_NEW)
                    .carType(CarType.SEDAN)
                    .color(Colors.DataTransferObjects.GREY)
                    .yearOfProduction("2021")
                    .build();

            public static final Car MERCEDES_BENZ = Car.builder()
                    .id(2L)
                    .mark(Marks.DataTransferObjects.MERCEDES_BENZ)
                    .model(Models.DataTransferObjects.C63S)
                    .fuelType(FuelTypes.DataTransferObjects.GAS)
                    .power(500L)
                    .engineCapacity(4000L)
                    .gearbox(Gearboxes.DataTransferObjects.AUTOMATIC)
                    .mileage(0L)
                    .drivingGear(DrivingGears.DataTransferObjects.AWD)
                    .state(State.BRAND_NEW)
                    .carType(CarType.COUPE)
                    .color(Colors.DataTransferObjects.BLACK)
                    .yearOfProduction("2021")
                    .build();
        }

        @NoArgsConstructor(access = AccessLevel.PRIVATE)
        public static class Entities {
            public static final CarEntity MERCEDES_BENZ = CarEntity.builder()
                    .id(2L)
                    .mark(Marks.Entities.MERCEDES_BENZ)
                    .model(Models.Entities.C63S)
                    .fuelType(FuelTypes.Entities.GAS)
                    .power(500L)
                    .engineCapacity(4000L)
                    .gearbox(Gearboxes.Entities.AUTOMATIC)
                    .mileage(0L)
                    .drivingGear(DrivingGears.Entities.AWD)
                    .state(State.BRAND_NEW)
                    .carType(CarType.COUPE)
                    .color(Colors.Entities.BLACK)
                    .yearOfProduction(LocalDate.of(2021, 1,1))
                    .build();

            public static final CarEntity MERCEDES_BENZ_UPDATED = CarEntity.builder()
                    .id(2L)
                    .mark(Marks.Entities.MERCEDES_BENZ)
                    .model(Models.Entities.C63S)
                    .fuelType(FuelTypes.Entities.GAS)
                    .power(700L)
                    .engineCapacity(4000L)
                    .gearbox(Gearboxes.Entities.AUTOMATIC)
                    .mileage(0L)
                    .drivingGear(DrivingGears.Entities.AWD)
                    .state(State.BRAND_NEW)
                    .carType(CarType.COUPE)
                    .color(Colors.Entities.RED)
                    .yearOfProduction(LocalDate.of(2021, 1,1))
                    .build();
        }
    }
}




