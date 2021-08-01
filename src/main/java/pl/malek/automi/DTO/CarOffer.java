package pl.malek.automi.DTO;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CarOffer {

    private Long id;

    private String title;

    private String description;

    private Car car;

    private User user;

    private BigDecimal price;

}
