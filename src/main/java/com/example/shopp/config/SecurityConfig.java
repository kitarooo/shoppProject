package com.example.shopp.config;

import com.example.shopp.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
@Configuration
public class SecurityConfig extends WebSecurityConfiguration{
    private final AuthProviderImpl authProvider;

    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }

    //authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    /*private final String[] PERMIT_ALL = {
            "/auth/**"
    };

    private final String[] USER_COMMON = {
            "/api/v1/users/**",
            "/api/v1/orders/createOrder",
            "/api/v2/**"
    };

    private final String[] ADMIN_COMMON = {
            "/api/v2/admin/**",
            "/api/v1/categories/**",
            "/api/v1/orders/**",
            "/api/v1/products/**"
    };*/




    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception {
        return http.csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request.requestMatchers("/auth/login", "/auth/registration")
                                .permitAll()
                                .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/auth/login")
                        .loginProcessingUrl("/process_login")
                        .defaultSuccessUrl("/main", true)
                        .failureUrl("/auth/login?error")
                )
                .logout(logout ->
                        logout.logoutUrl("/logout")
                                .logoutSuccessUrl("/auth/login"))
                .build();
    }
}
