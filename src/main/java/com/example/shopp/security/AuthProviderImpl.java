package com.example.shopp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    @Bean
    public PasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }
    private final UserDetailsService detailsService;
    @Autowired
    public AuthProviderImpl(UserDetailsService detailsService) {
        this.detailsService = detailsService;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();

        UserDetails userDetails = detailsService.loadUserByUsername(email);

        String password = authentication.getCredentials().toString();

        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,password, Collections.emptyList());
    }

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
