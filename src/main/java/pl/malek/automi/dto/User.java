package pl.malek.automi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.malek.automi.validators.annotation.ValidPassword;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Long id;

    @Email(message = "Email wrong format")
    @NotBlank(message = "Email may be not blank")
    private String email;

    @ValidPassword
    @NotBlank(message = "Password may be not blank")
    private String password;

    private LocalDate dateJoined;
    private Boolean isActive;

    private String role;
}
