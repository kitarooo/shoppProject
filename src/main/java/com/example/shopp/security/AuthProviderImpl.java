package com.example.shopp.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class AuthProviderImpl implements AuthenticationProvider {
    private final UserDetailsService detailsService;
   // private final PasswordEncoder encoder;
    @Autowired
    public AuthProviderImpl(UserDetailsService detailsService) {
        this.detailsService = detailsService;
        //this.encoder = encoder;
    }
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String email = authentication.getName();

        UserDetails userDetails = detailsService.loadUserByUsername(email);

        //String password = encoder.encode(authentication.getCredentials().toString());
        String password = authentication.getCredentials().toString();
        /*String presentedPassword = authentication.getCredentials().toString();

        if (!encoder.matches(presentedPassword, userDetails.getPassword())) {
            throw new BadCredentialsException("Bad password");
        }*/
        if (!password.equals(userDetails.getPassword())) {
            throw new BadCredentialsException("Incorrect password!");
        }

        return new UsernamePasswordAuthenticationToken(userDetails,password, Collections.emptyList());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return true;
    }
}
