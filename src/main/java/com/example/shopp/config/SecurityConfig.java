package com.example.shopp.config;

import com.example.shopp.security.AuthProviderImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfiguration;


@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfiguration {
    private final AuthProviderImpl authProvider;
    @Autowired
    public SecurityConfig(AuthProviderImpl authProvider) {
        this.authProvider = authProvider;
    }


    //authentication
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(authProvider);
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(request ->
                         request.requestMatchers("/auth/login")
                        .permitAll()
                        .anyRequest().authenticated())
                .formLogin(formLogin -> formLogin.loginPage("/auth/login")
                .loginProcessingUrl("/process_login")
                .defaultSuccessUrl("/main", true)
                .failureUrl("/auth/login?error")
        ).build();
    }
}
