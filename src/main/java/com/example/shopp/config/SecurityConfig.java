package com.example.shopp.config;

import com.example.shopp.security.util.JwtAuthenticationFilter;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;


@EnableWebSecurity
@Configuration
@RequiredArgsConstructor
public class SecurityConfig extends WebSecurityConfiguration {
    private final AuthenticationProvider authProvider;
    private final JwtAuthenticationFilter jwtAuthFilter;

    private final String[] PERMIT_ALL = {
            "/api/v2/**",
            "/swagger*/**",
            "/swagger-ui/**",
            "/shopp/swagger-ui.html",
            "/documentation/**",
            "/v3/api-docs/**"
    };

    private final String[] USER_COMMON = {
            "/api/v1/users/**",
            "/api/v1/orders/createOrder",
            "/api/v1/products/allProducts"
    };

    private final String[] ADMIN_COMMON = {
            "/api/v1/admin/**",
            "/api/v1/categories/**",
            "/api/v1/orders/**",
            "/api/v1/products/createProduct/{id}",
            "/api/v1/products/updateProduct/{id}"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ADMIN_COMMON).hasRole("ADMIN")
                        .requestMatchers(USER_COMMON).hasRole("USER")
                        .requestMatchers(PERMIT_ALL).permitAll().anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }
}
