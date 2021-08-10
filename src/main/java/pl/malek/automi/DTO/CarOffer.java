package pl.malek.automi.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.math.BigDecimal;

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

    private Car car;

    private User user;

    private BigDecimal price;

}
