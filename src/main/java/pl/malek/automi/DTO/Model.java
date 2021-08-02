package pl.malek.automi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Model {
    private Long id;

    @Size(min = 3, max = 24, message = "length (5-24)")
    @NotBlank(message = "Model field may be not blank")
    private String model;

    @NotNull(message = "Mark id is needed to define car model")
    private Long markId;
}
