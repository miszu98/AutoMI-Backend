package pl.malek.automi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Gearbox {
    private Long id;

    @Size(min = 3, max = 10, message = "length (3-10)")
    @NotBlank(message = "Driving gear field may be not blank")
    private String gearbox;
}
