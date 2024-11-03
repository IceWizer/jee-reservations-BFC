package com.bfv.reservation.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.bfv.reservation.security.jwt.JwtHeaderFilter;

@Configuration
public class SecurityConfig {

    @Bean
    SecurityFilterChain filterChain(HttpSecurity http, JwtHeaderFilter jwtHeaderFilter) throws Exception {
        http.authorizeHttpRequests(authorize -> {
            authorize.requestMatchers("/api/auth/login", "/api/auth/create").permitAll();
            authorize.requestMatchers("/api/v1/flights/save").hasRole("ADMIN");
            authorize.requestMatchers("/api/v1/flights/save/**").hasRole("ADMIN");
            authorize.requestMatchers("/api/v1/flights/delete/**").hasRole("ADMIN");
            authorize.requestMatchers("/api/v1/hotels/save").hasRole("ADMIN");
            authorize.requestMatchers("/api/v1/hotels/save/**").hasRole("ADMIN");
            authorize.requestMatchers("/api/v1/hotels/delete/**").hasRole("ADMIN");
            authorize.requestMatchers("/api/v1/cars/save").hasRole("ADMIN");
            authorize.requestMatchers("/api/v1/cars/save/**").hasRole("ADMIN");
            authorize.requestMatchers("/api/v1/cars/delete/**").hasRole("ADMIN");
            authorize.anyRequest().authenticated();
        });

        http.csrf(AbstractHttpConfigurer::disable);

        http.sessionManagement(management -> management.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtHeaderFilter, UsernamePasswordAuthenticationFilter.class);

        http.cors(Customizer.withDefaults());
        return http.build();
    }

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
