package pl.malek.automi.Entities;


import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;


@Data
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cars")
public class CarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "mark_id", referencedColumnName = "id")
    private MarkEntity mark;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", referencedColumnName = "id")
    private ModelEntity model;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fuel_type_id", referencedColumnName = "id")
    private FuelTypeEntity fuelType;

    private Long power;

    private Long engineCapacity;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driving_gear_id", referencedColumnName = "id")
    private GearboxEntity drivingGear;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "color_id", referencedColumnName = "id")
    private ColorEntity color;

    @JsonFormat()
    private LocalDate yearOfProduction;
}
