package pl.malek.automi.repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.entity.FuelTypeEntity;
import java.util.List;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class FuelTypeRepositoryTests {

    @Autowired
    private FuelTypeRepository fuelTypeRepository;

    @BeforeEach
    void setUp() {
        FuelTypeEntity diesel = FuelTypeEntity.builder().fuelTypeName("DIESEL").build();
        fuelTypeRepository.save(diesel);
    }

    @AfterEach
    void tearDown() {
        fuelTypeRepository.deleteAll();
    }

    @Test
    void shouldAddNewFuelType() {
        FuelTypeEntity gas = FuelTypeEntity.builder().fuelTypeName("GAS").build();
        fuelTypeRepository.save(gas);

        List<FuelTypeEntity> fuelTypes = fuelTypeRepository.findAll();

        assertEquals(2, fuelTypes.size());
        assertEquals("GAS", fuelTypes.get(1).getFuelTypeName());
    }

    @Test
    void shouldReturnAllFuelTypes() {
        List<FuelTypeEntity> fuelTypes = fuelTypeRepository.findAll();
        assertEquals(1, fuelTypes.size());
    }
}
