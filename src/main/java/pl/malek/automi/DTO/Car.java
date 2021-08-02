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

    private Long power;

    private Long engineCapacity;

    private DrivingGear drivingGear;

    private Color color;

    private String yearOfProduction;

}
