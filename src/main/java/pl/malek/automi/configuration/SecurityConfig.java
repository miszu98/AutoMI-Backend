package pl.malek.automi.configuration;


import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import static pl.malek.automi.utils.Endpoints.*;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final static String ROLE_ADMIN = "ADMIN";

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        super.configure(auth);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable();
        http.headers().disable();

        http.authorizeRequests()
                .antMatchers(HttpMethod.POST,
                        ROLES,
                        MODELS,
                        MARKS,
                        GEARBOX,
                        FUEL_TYPES,
                        DRIVING_GEARS,
                        COLORS
                ).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.PUT,
                        USERS,
                        ROLES,
                        MODELS,
                        MARKS,
                        GEARBOX,
                        FUEL_TYPES,
                        DRIVING_GEARS,
                        COLORS
                ).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE,
                        USERS,
                        ROLES,
                        MODELS,
                        MARKS,
                        GEARBOX,
                        FUEL_TYPES,
                        DRIVING_GEARS,
                        COLORS
                ).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.GET, "/**").permitAll()
                .antMatchers(HttpMethod.POST, USERS).permitAll()
                .antMatchers(HttpMethod.PUT, CAR_OFFERS).authenticated()
                .antMatchers(HttpMethod.DELETE, USERS).hasAuthority(ROLE_ADMIN)
                .antMatchers(HttpMethod.DELETE, CAR_OFFERS).authenticated()
                .anyRequest().permitAll();
    }
}
