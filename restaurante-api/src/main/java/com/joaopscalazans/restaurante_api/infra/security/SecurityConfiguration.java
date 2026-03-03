package com.joaopscalazans.restaurante_api.infra.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.joaopscalazans.restaurante_api.domain.user.User;
import com.joaopscalazans.restaurante_api.domain.user.UserRole;
import com.joaopscalazans.restaurante_api.repository.UserRepository;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) {
        return http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin()))
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/h2-console/**").permitAll()
                        .requestMatchers("/user/**").permitAll()
                        .requestMatchers("/reserve/**").hasRole("CLIENT")
                        .requestMatchers(HttpMethod.GET, "/dining-table").hasAnyRole("CLIENT","ADMIN")
                        .requestMatchers("/dining-table/**").hasRole("ADMIN")
                        .anyRequest().authenticated())
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CommandLineRunner createAdmin(UserRepository userRepository, PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByEmail("admin@email.com").isEmpty()) {
                User admin = new User();
                admin.setName("Administrador");
                admin.setEmail("admin@email.com");
                admin.setPassword(encoder.encode("123456"));
                admin.setRole(UserRole.ADMIN);
                userRepository.save(admin);
            }
        };
    }

}
