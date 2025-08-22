package com.muneesh.config;

import com.muneesh.Services.CustomUserDetails;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.config.http.SessionCreationPolicy;


@Configuration
@EnableWebSecurity //this annotation is used to activate SPRING web security for Rest apis & it activates the SecurityFilterChain
@RequiredArgsConstructor
@EnableMethodSecurity // ✅ Add this

public class SecurityConfig {
    private final CustomUserDetails details; // this is used to get the user details from the database
    private final JwtAuthenticationFilter jwtAuthenticationFilter; // this is used to filter the request and response
    private final PasswordConfig config;
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.
                csrf(csrf -> csrf.disable())// using Lambda expression for easy csrf means cross site request forgery
                .authorizeHttpRequests(check -> check.requestMatchers("/Auth/signup", "/Auth/login").permitAll()
                        .requestMatchers("/admin/**").hasRole("ADMIN") // this is for admin role
                        .requestMatchers("/doctor/**").hasRole("Doctor") // this is for doctor role
                        .requestMatchers("/patient/**").hasRole("Patient") // this is for patient
                        .requestMatchers("/nurse/**").hasRole("Nurse") // this is for nurse role
                        .anyRequest().authenticated()
                )
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
        return http.build();
    }
    @Bean
    public DaoAuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setUserDetailsService(details);
        provider.setPasswordEncoder(config.encoder()); // this is used to encode the password
        return provider;

    }
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}