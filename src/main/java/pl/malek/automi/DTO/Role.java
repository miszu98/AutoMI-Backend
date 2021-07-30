package pl.malek.automi.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Long id;

    @NotNull(message = "Role field may be not null")
    @NotBlank(message = "Role field may be not blank")
    private String role;


}
