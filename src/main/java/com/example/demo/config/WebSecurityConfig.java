package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            // Disable CSRF for tests
            .csrf(csrf -> csrf.disable())

            // Allow everything (IMPORTANT FOR TESTS)
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(
                        "/**",
                        "/swagger-ui/**",
                        "/v3/api-docs/**",
                        "/h2-console/**"
                ).permitAll()
                .anyRequest().permitAll()
            )

            // Disable default login
            .httpBasic(Customizer.withDefaults())
            .formLogin(form -> form.disable())

            // Needed for H2 console
            .headers(headers -> headers.frameOptions(frame -> frame.disable()));

        return http.build();
    }
}
