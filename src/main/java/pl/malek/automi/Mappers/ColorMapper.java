package pl.malek.automi.Mappers;


import org.springframework.stereotype.Component;
import pl.malek.automi.DTO.Color;
import pl.malek.automi.Entities.ColorEntity;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ColorMapper {


    public Color entityToDto(ColorEntity colorEntity) {
        return Color
                .builder()
                .id(colorEntity.getId())
                .colorName(colorEntity.getColorName())
                .build();
    }

    public ColorEntity dtoToEntity(Color color) {
        return ColorEntity
                .builder()
                .colorName(color.getColorName())
                .build();
    }

    public List<Color> entitiesToDto(List<ColorEntity> colorEntities) {
        return colorEntities
                .stream()
                .map(
                        colorEntity -> Color
                                .builder()
                                .id(colorEntity.getId())
                                .colorName(colorEntity.getColorName())
                                .build()
                ).collect(Collectors.toList());
    }
}
