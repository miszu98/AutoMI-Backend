package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.ColorEntity;
import pl.malek.automi.Entities.DrivingGearEntity;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ColorRepositoryTests {

    @Autowired
    private ColorRepository colorRepository;

    @BeforeEach
    void setUp() {
        ColorEntity black = ColorEntity.builder().colorName("BLACK").build();
        colorRepository.save(black);
    }

    @AfterEach
    void tearDown() {
        colorRepository.deleteAll();
    }

    @Test
    void shouldAddNewColor() {
        ColorEntity red = ColorEntity.builder().colorName("RED").build();
        colorRepository.save(red);

        List<ColorEntity> colors = colorRepository.findAll();

        assertEquals(2, colors.size());
        assertEquals("RED", colors.get(1).getColorName());
    }

    @Test
    void shouldReturnAllColors() {
        List<ColorEntity> colors = colorRepository.findAll();
        assertEquals(1, colors.size());
    }

}
