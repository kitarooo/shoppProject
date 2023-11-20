package com.example.shopp.config;

import com.example.shopp.util.JwtAuthenticationFilter;
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
            "/api/v2/**"
    };

    private final String[] USER_COMMON = {
            "/api/v1/users/**",
            "/api/v1/orders/createOrder"
    };

    private final String[] ADMIN_COMMON = {
            "/api/v1/admin/**",
            "/api/v1/categories/**",
            "/api/v1/orders/**",
            "/api/v1/products/**"
    };

    private final String[] test = {
            "/api/v2/**",
            "/api/v1/admin/**",
            "/swagger*/**",
            "/swagger-ui/**",
            "/shopp/swagger-ui.html"
    };

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers(ADMIN_COMMON).permitAll()
                        .requestMatchers(USER_COMMON).permitAll()
                        .requestMatchers(test).permitAll()
                        .requestMatchers(PERMIT_ALL)
                        .permitAll().anyRequest().authenticated())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authenticationProvider(authProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class).build();
    }

     /*@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request *//*.requestMatchers(ADMIN_COMMON).hasRole("Role.ROLE_ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v2/admin/allUsers").hasRole("ADMIN")
                                .requestMatchers(USER_COMMON).hasRole("USER")*//*
                                .requestMatchers("/auth/login", "/auth/registration").permitAll().anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/auth/login")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/api/v2/main", true)
                        .failureUrl("/auth/login?error")
                )
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/auth/login"))
                .build();

    }*/
}
