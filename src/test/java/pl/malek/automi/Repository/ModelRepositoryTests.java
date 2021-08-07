package pl.malek.automi.Repository;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.Entities.ModelEntity;
import java.util.List;
import java.util.Optional;

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
        Optional<MarkEntity> optionalMarkEntity = markRepository.findMarkEntityByMark("Mercedes-Benz");
        boolean isExist = optionalMarkEntity.isPresent();
        MarkEntity markEntity;
        if (isExist) {
            markEntity = optionalMarkEntity.get();
        } else {
            markEntity = new MarkEntity();
        }
        assertEquals("C63s",markEntity.getModels().get(0).getModel());
        assertEquals(1, markEntity.getModels().size());
    }

    @Test
    void shouldCheckHowManyModelsInDatabase() {
        ModelEntity M5 = ModelEntity.builder().model("M5").markEntity(bmw).build();
        ModelEntity M2 = ModelEntity.builder().model("M2").markEntity(bmw).build();
        ModelEntity C63s = ModelEntity.builder().model("C63s").markEntity(mercedes).build();
        modelRepository.saveAll(List.of(M2, M5, C63s));
        assertEquals(3, ((List<ModelEntity>) modelRepository.findAll()).size());

        boolean isExist;
        Optional<MarkEntity> optionalMarkEntity;
        MarkEntity markEntity;

        optionalMarkEntity = markRepository.findMarkEntityByMark("BMW");
        isExist = optionalMarkEntity.isPresent();
        if (isExist) {
            markEntity = optionalMarkEntity.get();
        } else {
            markEntity = new MarkEntity();
        }
        assertEquals(2, markEntity.getModels().size());

        optionalMarkEntity = markRepository.findMarkEntityByMark("Mercedes-Benz");
        isExist = optionalMarkEntity.isPresent();
        if (isExist) {
            markEntity = optionalMarkEntity.get();
        } else {
            markEntity = new MarkEntity();
        }
        assertEquals(1, markEntity.getModels().size());
    }

    @Test
    void shouldCheckIfDeleteMarkDeleteAlsoModels() {
        ModelEntity M5 = ModelEntity.builder().model("M5").markEntity(bmw).build();
        ModelEntity M2 = ModelEntity.builder().model("M2").markEntity(bmw).build();
        modelRepository.saveAll(List.of(M2, M5));
        Optional<MarkEntity> optionalMarkEntity = markRepository.findMarkEntityByMark("BMW");
        boolean isExist = optionalMarkEntity.isPresent();
        MarkEntity markEntity;
        if (isExist) {
            markEntity = optionalMarkEntity.get();
        } else {
            markEntity = new MarkEntity();
        }
        assertEquals(2, markEntity.getModels().size());
        markRepository.deleteAll();
        assertEquals(0, ((List<ModelEntity>) modelRepository.findAll()).size());
    }
}
