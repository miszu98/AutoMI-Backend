package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.GearboxEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class GearboxRepositoryTests {

    @Autowired
    private GearboxRepository gearboxRepository;

    @BeforeEach
    void setUp() {
        GearboxEntity automat = GearboxEntity.builder().gearbox("AUTOMAT").build();
        gearboxRepository.save(automat);
    }

    @AfterEach
    void tearDown() {
        gearboxRepository.deleteAll();
    }

    @Test
    void shouldAddNewDrivingGear() {
        GearboxEntity manual = GearboxEntity.builder().gearbox("MANUAL").build();
        gearboxRepository.save(manual);

        List<GearboxEntity> drivingGears = gearboxRepository.findAll();

        assertEquals(2, drivingGears.size());
        assertEquals("MANUAL", drivingGears.get(1).getGearbox());
    }

    @Test
    void shouldReturnAllDrivingGears() {
        List<GearboxEntity> drivingGears = gearboxRepository.findAll();
        assertEquals(1, drivingGears.size());
    }
}
