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
public class FuelType {
    private Long id;

    @Size(min = 3, max = 10, message = "length (3-10)")
    @NotBlank(message = "Fuel type field may be not blank")
    private String fuelTypeName;
}
