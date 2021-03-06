package pl.malek.automi.mapper;

import org.springframework.stereotype.Component;
import pl.malek.automi.dto.Mark;
import pl.malek.automi.entity.MarkEntity;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class MarkMapper {
    public MarkEntity dtoToEntity(Mark mark) {
        return MarkEntity
                .builder()
                .id(mark.getId())
                .mark(mark.getMark())
                .build();
    }

    public List<Mark> entitiesToDto(List<MarkEntity> markEntities) {
        return markEntities
                .stream()
                .map(markEntity -> Mark
                        .builder()
                        .id(markEntity.getId())
                        .mark(markEntity.getMark())
                        .build()
                ).collect(Collectors.toList());
    }

    public Mark entityToDto(MarkEntity markEntity) {
        return Mark
                .builder()
                .id(markEntity.getId())
                .mark(markEntity.getMark())
                .build();
    }
}
