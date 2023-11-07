package exercise;

import exercise.model.UserRole;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.configurers.SessionManagementConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.http.HttpMethod.DELETE;
import static org.springframework.http.HttpMethod.GET;
import static org.springframework.http.HttpMethod.POST;


@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class WebSecurityConfig {

    public final UserDetailsServiceImpl userDetailsService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        var user = UserRole.USER.name();
        var admin = UserRole.ADMIN.name();
        return http.csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(SessionManagementConfigurer::disable)
                .authorizeHttpRequests(request -> request
                        .requestMatchers(GET, "/").permitAll()
                        .requestMatchers(POST, "/users").permitAll()
                        .requestMatchers(GET, "/users/**").hasAnyAuthority(user, admin)
                        .requestMatchers(DELETE, "/users/*").hasAuthority(admin)
                        .anyRequest()
                        .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }


}
