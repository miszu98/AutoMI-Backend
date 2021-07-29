package pl.malek.automi.Mappers;

import pl.malek.automi.DTO.Model;
import pl.malek.automi.Entities.ModelEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ModelMapper {
    public static ModelEntity dtoToEntity(Model model) {
        return ModelEntity
                .builder()
                .model(model.getModel())
                .build();
    }

    public static List<Model> entitiesToDto(Iterable<ModelEntity> modelEntities) {
        var list = new ArrayList<ModelEntity>();
        modelEntities.forEach(list::add);
        return list
                .stream()
                .map(model -> Model
                    .builder()
                    .id(model.getId())
                    .model(model.getModel()).build()
                ).collect(Collectors.toList());
    }

    public static Model entityToDto(ModelEntity modelEntity) {
        return Model
                .builder()
                .id(modelEntity.getId())
                .model(modelEntity.getModel())
                .build();
    }
}
