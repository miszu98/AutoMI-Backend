package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.Entities.ModelEntity;
import pl.malek.automi.UtilsMI.GenericTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class ModelRepositoryTests {

    @Autowired private MarkRepository markRepository;
    @Autowired private ModelRepository modelRepository;

    private MarkEntity mercedes;
    private MarkEntity bmw;

    @BeforeEach
    void setUp() {
        mercedes = MarkEntity.builder().mark("Mercedes-Benz").build();
        bmw = MarkEntity.builder().mark("BMW").build();
        markRepository.saveAll(List.of(mercedes, bmw));
    }

    @AfterEach
    void tearDown() {
        markRepository.deleteAll();
    }

    @Test
    void shouldAddModelToProvidedMark() {
        var C63s = ModelEntity.builder().model("C63s").markEntity(mercedes).build();
        modelRepository.save(C63s);

        var markEntity = GenericTest
                .tryToGetEntityObject(markRepository.findMarkEntityByMark("Mercedes-Benz"));

        assertEquals("C63s",markEntity.getModels().get(0).getModel());
        assertEquals(1, markEntity.getModels().size());
    }

    @Test
    void shouldCheckHowManyModelsInDatabase() {
        var M5 = ModelEntity.builder().model("M5").markEntity(bmw).build();
        var M2 = ModelEntity.builder().model("M2").markEntity(bmw).build();
        var C63s = ModelEntity.builder().model("C63s").markEntity(mercedes).build();
        modelRepository.saveAll(List.of(M2, M5, C63s));

        var bmwEntity = GenericTest
                .tryToGetEntityObject(markRepository.findMarkEntityByMark("BMW"));
        assertEquals(2, bmwEntity.getModels().size());

        var mercedesEntity = GenericTest
                .tryToGetEntityObject(markRepository.findMarkEntityByMark("Mercedes-Benz"));

        assertEquals(3, ((List<ModelEntity>) modelRepository.findAll()).size());
        assertEquals(1, mercedesEntity.getModels().size());
    }

    @Test
    void shouldCheckIfDeleteMarkDeleteAlsoModels() {
        var M5 = ModelEntity.builder().model("M5").markEntity(bmw).build();
        var M2 = ModelEntity.builder().model("M2").markEntity(bmw).build();
        modelRepository.saveAll(List.of(M2, M5));

        var markEntity= GenericTest.tryToGetEntityObject(markRepository.findMarkEntityByMark("BMW"));

        assertEquals(2, markEntity.getModels().size());
        markRepository.deleteAll();
        assertEquals(0, ((List<ModelEntity>) modelRepository.findAll()).size());
    }
}
