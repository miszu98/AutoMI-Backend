package pl.malek.automi.DTO;

import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Role {

    private Long id;

    @Size(min = 4, max = 12, message = "length (5-12)")
    @NotBlank(message = "Role field may be not blank")
    private String role;


}
