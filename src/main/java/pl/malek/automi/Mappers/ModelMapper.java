package pl.malek.automi.Mappers;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Entities.ModelEntity;
import pl.malek.automi.Exceptions.MarkNotFoundException;
import pl.malek.automi.Service.MarkService;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class ModelMapper {

    private final MarkService markService;

    public ModelEntity dtoToEntity(Model model) throws MarkNotFoundException {
        return ModelEntity
                .builder()
                .model(model.getModel())
                .markEntity(markService.getById(model.getMarkId()))
                .build();
    }

    public static List<Model> entitiesToDto(Iterable<ModelEntity> modelEntities) {
        var list = new ArrayList<ModelEntity>();
        modelEntities.forEach(list::add);
        return list
                .stream()
                .map(modelEntity -> Model
                    .builder()
                    .id(modelEntity.getId())
                    .model(modelEntity.getModel())
                        .markId(modelEntity.getId())
                        .build()
                ).collect(Collectors.toList());
    }

    public static Model entityToDto(ModelEntity modelEntity) {
        return Model
                .builder()
                .id(modelEntity.getId())
                .model(modelEntity.getModel())
                .markId(modelEntity.getMarkEntity().getId())
                .build();
    }
}
