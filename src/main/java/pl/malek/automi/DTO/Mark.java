package pl.malek.automi.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Mark {
    private Long id;

    @Size(min = 3, max = 24, message = "length (3-24)")
    @NotBlank(message = "Mark field may be not blank")
    private String mark;
}
