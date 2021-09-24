package pl.malek.automi.mapper;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.dto.Model;
import pl.malek.automi.entity.ModelEntity;
import pl.malek.automi.exception.MarkNotFoundException;
import pl.malek.automi.repository.MarkRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ModelMapper {

    private final MarkRepository markRepository;

    public ModelEntity dtoToEntity(Model model) throws MarkNotFoundException {
        return ModelEntity
                .builder()
                .model(model.getModel())
                .markEntity(markRepository.findById(model.getMarkId()).orElseThrow(
                        () -> new MarkNotFoundException(
                                "Mark with id: " + model.getMarkId() + " not found"
                        )
                ))
                .build();
    }

    public List<Model> entitiesToDto(Iterable<ModelEntity> modelEntities) {
        var list = new ArrayList<ModelEntity>();
        modelEntities.forEach(list::add);
        return list
                .stream()
                .map(modelEntity -> Model
                    .builder()
                    .id(modelEntity.getId())
                    .model(modelEntity.getModel())
                        .markId(modelEntity.getMarkEntity().getId())
                        .build()
                ).collect(Collectors.toList());
    }

    public Model entityToDto(ModelEntity modelEntity) {
        return Model
                .builder()
                .id(modelEntity.getId())
                .model(modelEntity.getModel())
                .markId(modelEntity.getMarkEntity().getId())
                .build();
    }
}
