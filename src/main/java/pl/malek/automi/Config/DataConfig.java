package pl.malek.automi.Config;

import org.springframework.context.annotation.Configuration;
import pl.malek.automi.Repository.*;


@Configuration
public class DataConfig {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private CarRepository carRepository;
    private ModelRepository modelRepository;
    private MarkRepository markRepository;
    private ColorRepository colorRepository;
    private FuelTypeRepository fuelTypeRepository;
    private DrivingGearRepository drivingGearRepository;
    private CarOfferRepository carOfferRepository;


    public DataConfig(CarOfferRepository carOfferRepository, UserRepository userRepository, RoleRepository roleRepository, CarRepository carRepository, ModelRepository modelRepository, MarkRepository markRepository, ColorRepository colorRepository, FuelTypeRepository fuelTypeRepository, DrivingGearRepository drivingGearRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.carRepository = carRepository;
        this.modelRepository = modelRepository;
        this.markRepository = markRepository;
        this.colorRepository = colorRepository;
        this.fuelTypeRepository = fuelTypeRepository;
        this.drivingGearRepository = drivingGearRepository;
        this.carOfferRepository = carOfferRepository;
//
//        RoleEntity role = RoleEntity.builder().roleName("ADMIN").build();
//        roleRepository.save(role);
//
//        UserEntity user = UserEntity
//                .builder()
//                .email("admin@admin.com")
//                .password("admin")
//                .isActive(true)
//                .roleEntity(role)
//                .build();
//        userRepository.save(user);
//
//        MarkEntity mark = MarkEntity.builder().mark("BMW").build();
//        ModelEntity model = ModelEntity.builder().model("M5").build();
//        FuelTypeEntity fuelType = FuelTypeEntity.builder().fuelTypeName("BENZINE").build();
//        DrivingGearEntity drivingGear = DrivingGearEntity.builder().drivingGearName("AUTOMAT").build();
//        ColorEntity color = ColorEntity.builder().colorName("BLACK").build();
//
//        markRepository.save(mark);
//        modelRepository.save(model);
//        fuelTypeRepository.save(fuelType);
//        drivingGearRepository.save(drivingGear);
//        colorRepository.save(color);
//
//        CarEntity car = CarEntity
//                .builder()
//                .mark(mark)
//                .model(model)
//                .fuelType(fuelType)
//                .drivingGear(drivingGear)
//                .color(color)
//                .power(500L)
//                .engineCapacity(2000L)
//                .yearOfProduction(LocalDate.now())
//                .build();
//
//        carRepository.save(car);
//        CarOfferEntity offer = CarOfferEntity
//                .builder()
//                .title("Tytul oferty")
//                .description("opisik")
//                .carEntity(car)
//                .userEntity(user)
//                .price(new BigDecimal(100000))
//                .build();
//
//        carOfferRepository.save(offer);

    }
}
