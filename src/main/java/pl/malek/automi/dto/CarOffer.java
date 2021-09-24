package pl.malek.automi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarOffer {

    private Long id;

    @Size(min = 5, max = 100, message = "length (5-100)")
    @NotBlank(message = "Title field may be not blank")
    private String title;

    @Size(min = 50, max = 512, message = "length (50-512)")
    @NotBlank(message = "Description field may be not blank")
    private String description;

    @Valid
    @NotNull(message = "Car cannot be null")
    private Car car;

    @NotNull(message = "User cannot be null")
    private User user;

    @NotNull(message = "Price cannot be null")
    private BigDecimal price;

    @Size(min = 3, max = 15, message = "length (3-15)")
    @NotBlank(message = "City cannot be null")
    private String city;

    private List<Image> images;

    private LocalDate createdAt;

}
