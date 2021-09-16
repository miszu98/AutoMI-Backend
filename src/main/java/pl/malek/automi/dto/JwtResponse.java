package pl.malek.automi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1;

    private String email;
    private String token;
    private String role;

    public JwtResponse(String token, String email, String role) {
        this.email = email;
        this.token = token;
        this.role = role;
    }

}
