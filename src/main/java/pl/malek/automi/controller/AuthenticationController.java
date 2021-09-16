package pl.malek.automi.controller;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import pl.malek.automi.configuration.JwtTokenUtil;
import pl.malek.automi.dto.JwtRequest;
import pl.malek.automi.dto.JwtResponse;
import pl.malek.automi.service.AuthenticationService;
import pl.malek.automi.service.Impl.JwtUserDetailsServiceImpl;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/auth")
public class AuthenticationController {

    private AuthenticationService authenticationService;

    private JwtTokenUtil jwtTokenUtil;

    private JwtUserDetailsServiceImpl userDetailsService;

    @PostMapping("/")
    public ResponseEntity<JwtResponse> createAuthenticationToken(@RequestBody JwtRequest authRequest) throws Exception {
        authenticationService.authenticate(authRequest.getEmail(), authRequest.getPassword());

        final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getEmail());

        final String token = jwtTokenUtil.generateToken(userDetails);
        List<String> roles = userDetails.getAuthorities().stream().map(role -> role.getAuthority()).collect(Collectors.toList());
        return ResponseEntity.status(HttpStatus.OK).body(new JwtResponse(token, userDetails.getUsername(), roles.get(0)));
    }
}
