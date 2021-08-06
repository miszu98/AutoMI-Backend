package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.MarkEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class MarkRepositoryTests {


    @Autowired
    private MarkRepository markRepository;


    @BeforeEach
    void setUp() {
        MarkEntity markEntity = MarkEntity
                                    .builder()
                                    .mark("Mercedes-Benz")
                                    .build();
        markRepository.save(markEntity);
    }

    @AfterEach
    void tearDown() {
        markRepository.deleteAll();
    }

    @Test
    void shouldCheckIfMarkWithNameExist() {
        MarkEntity markEntity = markRepository.findMarkEntityByMark("Mercedes-Benz").get();
        assertEquals("Mercedes-Benz", markEntity.getMark());
    }

    @Test
    void shouldCheckIfMarkWithNameNotExist() {
        boolean isExist = markRepository.findMarkEntityByMark("AUDI").isPresent();
        assertFalse(isExist);
    }

    @Test
    void shouldAddNewMark() {
        MarkEntity markEntity = MarkEntity.builder().mark("BMW").build();
        markRepository.save(markEntity);
        assertEquals(2, markRepository.findAll().size());
    }

    @Test
    void shouldUpdateMarkName() {
        MarkEntity markEntity = markRepository.findMarkEntityByMark("Mercedes-Benz").get();
        markEntity.setMark("Mercedes");
        markRepository.save(markEntity);
        assertEquals(1, markRepository.findAll().size());
        assertEquals("Mercedes", markRepository.findMarkEntityByMark("Mercedes").get().getMark());
    }

    @Test
    void shouldClearMarkRecords() {
        markRepository.deleteAll();
        assertEquals(0, markRepository.findAll().size());
    }

    @Test
    void shouldCheckCountOfMarks() {
        assertEquals(1, markRepository.findAll().size());
    }
}
