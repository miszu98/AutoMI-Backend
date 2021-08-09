package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.*;
import pl.malek.automi.GenericCarTest;


@SpringBootTest
public class CarRepositoryTests {

    @Autowired private MarkRepository markRepository;
    @Autowired private ModelRepository modelRepository;
    @Autowired private FuelTypeRepository fuelTypeRepository;
    @Autowired private DrivingGearRepository drivingGearRepository;
    @Autowired private ColorRepository colorRepository;
    @Autowired private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        MarkEntity markEntity = MarkEntity.builder().mark("Mercedes-Benz").build();
        ModelEntity modelEntity = ModelEntity.builder().model("C63s").build();
        FuelTypeEntity fuelTypeEntity = FuelTypeEntity.builder().fuelTypeName("GAS").build();
        DrivingGearEntity drivingGearEntity = DrivingGearEntity.builder().drivingGearName("AUTOMAT").build();
        ColorEntity colorEntity = ColorEntity.builder().colorName("BLACK").build();
        markRepository.save(markEntity);
        modelRepository.save(modelEntity);
        fuelTypeRepository.save(fuelTypeEntity);
        drivingGearRepository.save(drivingGearEntity);
        colorRepository.save(colorEntity);
    }

    @AfterEach
    void tearDown() {
        markRepository.deleteAll();
        modelRepository.deleteAll();
        fuelTypeRepository.deleteAll();
        drivingGearRepository.deleteAll();
        colorRepository.deleteAll();
    }


    @Test
    void shouldAddCarOffer() {
        var markEntity = GenericCarTest.tryToGetEntityObject(markRepository.findMarkEntityByMark("Mercedes-Benz"));
        var modelEntity = GenericCarTest.tryToGetEntityObject(modelRepository.findByModel("C63s"));
        var fuelTypeEntity = GenericCarTest.tryToGetEntityObject(fuelTypeRepository.findFuelTypeEntityByFuelTypeName("GAS"));
        var drivingGearEntity = GenericCarTest.tryToGetEntityObject(drivingGearRepository.findDrivingGearEntityByDrivingGearName("AUTOMAT"));
        var colorEntity = GenericCarTest.tryToGetEntityObject(colorRepository.findColorEntityByColorName("BLACK"));


        CarEntity carEntity = CarEntity.builder()
                .mark(markEntity)
                .model(modelEntity)
                .fuelType(fuelTypeEntity)
                .power(625L)
                .engineCapacity(4000L)
                .drivingGear(drivingGearEntity)
                .color(colorEntity)
                .build();
        carRepository.save(carEntity);

        assertEquals(1, carRepository.findAll().size());
        assertEquals("BLACK",
                GenericCarTest
                        .tryToGetEntityObject(carRepository.findCarEntityByModel(modelEntity))
                        .getColor()
                        .getColorName());
    }
}
