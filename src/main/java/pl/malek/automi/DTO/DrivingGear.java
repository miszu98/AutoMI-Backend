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
public class DrivingGear {

    private Long id;

    @NotBlank(message = "Driving gear may not be blank")
    @Size(min = 2, max = 5, message = "Driving gear: Length(2 - 5)")
    private String drivingGear;
}
