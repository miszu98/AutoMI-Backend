package pl.malek.automi.configuration;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static pl.malek.automi.utils.Endpoints.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private static final String ROLE_ADMIN = "ADMIN";
    private static final String[] ENDPOINTS = {
            USERS,
            ROLES,
            MODELS,
            MARKS,
            GEARBOX,
            FUEL_TYPES,
            DRIVING_GEARS,
            COLORS
    };

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();

        http.cors()
                .and()
                .authorizeRequests()
                .antMatchers(HttpMethod.POST, ENDPOINTS).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.POST, USERS).permitAll()
                .antMatchers(HttpMethod.PUT, ENDPOINTS).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT, CAR_OFFERS).authenticated()
                .antMatchers(HttpMethod.DELETE, ENDPOINTS).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, USERS).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, CAR_OFFERS).authenticated()
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .anyRequest().permitAll();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Collections.singletonList("http://localhost:4200/"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE"));
        configuration.setAllowedHeaders(Arrays.asList("authorization"));
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }




}
