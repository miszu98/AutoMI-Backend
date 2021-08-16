package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.UtilsMI.GenericTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
public class MarkRepositoryTests {


    @Autowired
    private MarkRepository markRepository;


    @BeforeEach
    void setUp() {
        var markEntity = MarkEntity
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
        var markEntity = GenericTest.tryToGetEntityObject(markRepository.findMarkEntityByMark("Mercedes-Benz"));
        assertEquals("Mercedes-Benz", markEntity.getMark());
    }

    @Test
    void shouldCheckIfMarkWithNameNotExist() {
        var isExist = markRepository.findMarkEntityByMark("AUDI").isPresent();
        assertFalse(isExist);
    }

    @Test
    void shouldAddNewMark() {
        var markEntity = MarkEntity.builder().mark("BMW").build();
        markRepository.save(markEntity);
        assertEquals(2, markRepository.findAll().size());
    }

    @Test
    void shouldUpdateMarkName() {
        var markEntity = GenericTest.tryToGetEntityObject(markRepository.findMarkEntityByMark("Mercedes-Benz"));
        markEntity.setMark("Mercedes-Updated");
        markRepository.save(markEntity);

        assertEquals(1, markRepository.findAll().size());
        assertEquals("Mercedes-Updated", markRepository.findMarkEntityByMark("Mercedes-Updated").orElse(new MarkEntity()).getMark());
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
