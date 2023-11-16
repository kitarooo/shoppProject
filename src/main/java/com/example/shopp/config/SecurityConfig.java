package com.example.shopp.config;

import com.example.shopp.dto.Enums.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.annotation.web.servlet.configuration.WebMvcSecurityConfiguration;
import org.springframework.security.web.SecurityFilterChain;


@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfiguration {
    private final AuthenticationProvider authProvider;

    @Autowired
    public SecurityConfig(AuthenticationProvider authProvider) {
        this.authProvider = authProvider;
    }

    //authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    private final String[] PERMIT_ALL = {
            "/auth/**"
    };

    private final String[] USER_COMMON = {
            "/api/v1/users/**",
            "/api/v1/orders/createOrder",
    };

    private final String[] ADMIN_COMMON = {
            "/api/v2/admin/allUsers",
            "/api/v1/categories/**",
            "/api/v1/orders/**",
            "/api/v1/products/**"
    };




    /*@Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/auth/login", "/auth/registration")
                                .permitAll()
                                .anyRequest().authenticated())
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

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws  Exception {

        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request .requestMatchers(ADMIN_COMMON).hasRole("ADMIN")
                                .requestMatchers(HttpMethod.GET, "/api/v2/admin/allUsers").hasRole("ADMIN")
                                .requestMatchers(USER_COMMON).hasRole("USER")
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

    }
}
