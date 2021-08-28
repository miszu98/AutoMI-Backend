package pl.malek.automi.service.Impl;

import lombok.AllArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.malek.automi.entity.UserEntity;
import pl.malek.automi.repository.UserRepository;

import javax.transaction.Transactional;
import java.util.Collections;
import java.util.Optional;

@Service
@AllArgsConstructor
public class JwtUserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<UserEntity> optionalUser = userRepository.findByEmail(email);
        UserEntity user = optionalUser.orElseThrow(
                () -> new UsernameNotFoundException(
                        String.format("User with email: %s not exist", email)
                )
        );
        System.out.println(user.getRoleEntity().getRoleName());
        return new User(
                user.getEmail(),
                user.getPassword(),
                true, true, true, true,
                Collections.singleton(new SimpleGrantedAuthority(user.getRoleEntity().getRoleName()))
        );
    }
}
