package pl.malek.automi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    private Mark mark;

    private Model model;

    private FuelType fuelType;

    @NotNull(message = "Power field may be not null")
    private Long power;

    @NotNull(message = "Engine capactiy field may be not null")
    private Long engineCapacity;

    private DrivingGear drivingGear;

    private Color color;

    @NotBlank(message = "Year of production field may be not null")
    private String yearOfProduction;

}
