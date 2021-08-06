package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.Entities.ModelEntity;

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
        ModelEntity C63s = ModelEntity.builder().model("C63s").markEntity(mercedes).build();
        modelRepository.save(C63s);
        MarkEntity merc = markRepository.findMarkEntityByMark("Mercedes-Benz").get();
        assertEquals("C63s",merc.getModels().get(0).getModel());
        assertEquals(1, merc.getModels().size());
    }

    @Test
    void shouldCheckHowManyModelsInDatabase() {
        ModelEntity M5 = ModelEntity.builder().model("M5").markEntity(bmw).build();
        ModelEntity M2 = ModelEntity.builder().model("M2").markEntity(bmw).build();
        ModelEntity C63s = ModelEntity.builder().model("C63s").markEntity(mercedes).build();
        modelRepository.saveAll(List.of(M2, M5, C63s));
        assertEquals(3, ((List<ModelEntity>) modelRepository.findAll()).size());
        assertEquals(2, markRepository.findMarkEntityByMark("BMW").get().getModels().size());
        assertEquals(1, markRepository.findMarkEntityByMark("Mercedes-Benz").get().getModels().size());
    }

    @Test
    void shouldCheckIfDeleteMarkDeleteAlsoModels() {
        ModelEntity M5 = ModelEntity.builder().model("M5").markEntity(bmw).build();
        ModelEntity M2 = ModelEntity.builder().model("M2").markEntity(bmw).build();
        modelRepository.saveAll(List.of(M2, M5));
        assertEquals(2, markRepository.findMarkEntityByMark("BMW").get().getModels().size());
        markRepository.deleteAll();
        assertEquals(0, ((List<ModelEntity>) modelRepository.findAll()).size());
    }
}
