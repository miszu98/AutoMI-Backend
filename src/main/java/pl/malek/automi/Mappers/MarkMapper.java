package pl.malek.automi.Mappers;

import pl.malek.automi.DTO.Mark;
import pl.malek.automi.DTO.Model;
import pl.malek.automi.Entities.MarkEntity;
import pl.malek.automi.Entities.ModelEntity;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class MarkMapper {
    public static MarkEntity dtoToEntity(Mark mark) {
        return MarkEntity
                .builder()
                .mark(mark.getMark())
                .build();
    }

    public static List<Mark> entitiesToDto(Iterable<MarkEntity> markEntities) {
        var list = new ArrayList<MarkEntity>();
        markEntities.forEach(list::add);
        return list
                .stream()
                .map(mark -> Mark
                        .builder()
                        .id(mark.getId())
                        .mark(mark.getMark()).build()
                ).collect(Collectors.toList());
    }

    public static Mark entityToDto(MarkEntity markEntity) {
        return Mark
                .builder()
                .id(markEntity.getId())
                .mark(markEntity.getMark())
                .build();
    }
}
