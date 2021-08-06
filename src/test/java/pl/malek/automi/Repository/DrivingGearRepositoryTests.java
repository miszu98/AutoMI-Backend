package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.DrivingGearEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DrivingGearRepositoryTests {

    @Autowired
    private DrivingGearRepository drivingGearRepository;

    @BeforeEach
    void setUp() {
        DrivingGearEntity automat = DrivingGearEntity.builder().drivingGearName("AUTOMAT").build();
        drivingGearRepository.save(automat);
    }

    @AfterEach
    void tearDown() {
        drivingGearRepository.deleteAll();
    }

    @Test
    void shouldAddNewDrivingGear() {
        DrivingGearEntity manual = DrivingGearEntity.builder().drivingGearName("MANUAL").build();
        drivingGearRepository.save(manual);

        List<DrivingGearEntity> drivingGears = drivingGearRepository.findAll();

        assertEquals(2, drivingGears.size());
        assertEquals("MANUAL", drivingGears.get(1).getDrivingGearName());
    }

    @Test
    void shouldReturnAllDrivingGears() {
        List<DrivingGearEntity> drivingGears = drivingGearRepository.findAll();
        assertEquals(1, drivingGears.size());
    }
}
