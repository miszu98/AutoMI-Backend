package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import static org.junit.jupiter.api.Assertions.assertEquals;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.*;
import pl.malek.automi.UtilsMI.GenericTest;


@SpringBootTest
public class CarRepositoryTests {

    @Autowired private MarkRepository markRepository;
    @Autowired private ModelRepository modelRepository;
    @Autowired private FuelTypeRepository fuelTypeRepository;
    @Autowired private GearboxRepository gearboxRepository;
    @Autowired private ColorRepository colorRepository;
    @Autowired private CarRepository carRepository;

    @BeforeEach
    void setUp() {
        MarkEntity markEntity = MarkEntity.builder().mark("Mercedes-Benz").build();
        ModelEntity modelEntity = ModelEntity.builder().model("C63s").markEntity(markEntity).build();
        FuelTypeEntity fuelTypeEntity = FuelTypeEntity.builder().fuelTypeName("GAS").build();
        GearboxEntity gearboxEntity = GearboxEntity.builder().gearbox("AUTOMAT").build();
        ColorEntity colorEntity = ColorEntity.builder().colorName("BLACK").build();
        markRepository.save(markEntity);
        modelRepository.save(modelEntity);
        fuelTypeRepository.save(fuelTypeEntity);
        gearboxRepository.save(gearboxEntity);
        colorRepository.save(colorEntity);
    }

    @AfterEach
    void tearDown() {
        markRepository.deleteAll();
        modelRepository.deleteAll();
        fuelTypeRepository.deleteAll();
        gearboxRepository.deleteAll();
        colorRepository.deleteAll();
    }


    @Test
    void shouldAddCarOffer() {
        var markEntity = GenericTest.tryToGetEntityObject(markRepository.findMarkEntityByMark("Mercedes-Benz"));
        var modelEntity = GenericTest.tryToGetEntityObject(modelRepository.findByModel("C63s"));
        var fuelTypeEntity = GenericTest.tryToGetEntityObject(fuelTypeRepository.findFuelTypeEntityByFuelTypeName("GAS"));
        var gearboxEntity = GenericTest.tryToGetEntityObject(gearboxRepository.findGearboxEntitiesByGearbox("AUTOMAT"));
        var colorEntity = GenericTest.tryToGetEntityObject(colorRepository.findColorEntityByColorName("BLACK"));


        CarEntity carEntity = CarEntity.builder()
                .mark(markEntity)
                .model(modelEntity)
                .fuelType(fuelTypeEntity)
                .power(625L)
                .engineCapacity(4000L)
                .gearbox(gearboxEntity)
                .color(colorEntity)
                .build();

        carRepository.save(carEntity);


        assertEquals(1, carRepository.findAll().size());
        assertEquals("BLACK",
                GenericTest
                        .tryToGetEntityObject(carRepository.findCarEntityByModel(modelEntity))
                        .getColor()
                        .getColorName());
    }
}
