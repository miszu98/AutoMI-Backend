package pl.malek.automi.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
public class JwtResponse implements Serializable {
    private static final long serialVersionUID = 1;

    private String token;

    public JwtResponse(String token) {
        this.token = token;
    }

}
