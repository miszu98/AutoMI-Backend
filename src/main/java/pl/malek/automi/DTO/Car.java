package pl.malek.automi.DTO;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.malek.automi.Entities.DrivingGearEntity;
import pl.malek.automi.Enums.CarType;
import pl.malek.automi.Enums.State;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {

    private Long id;

    @NotNull(message = "Mark cannot be null")
    private Mark mark;

    @NotNull(message = "Model cannot be null")
    private Model model;

    @NotNull(message = "Fuel type cannot be null")
    private FuelType fuelType;

    @NotNull(message = "Power field may be not null")
    private Long power;

    @NotNull(message = "Engine capactiy field may be not null")
    private Long engineCapacity;

    @NotNull(message = "Gearbox cannot be null")
    private Gearbox gearbox;

    @NotNull(message = "Mileage cannot be null")
    private Long mileage;

    @NotNull(message = "Driving gear cannot be null")
    private DrivingGear drivingGear;

    @NotNull(message = "State cannot be null")
    private State state;

    @NotNull(message = "Car type cannot be null")
    private CarType carType;

    @NotNull(message = "Color cannot be null")
    private Color color;

    @NotBlank(message = "Year of production field may be not null")
    private String yearOfProduction;

}
