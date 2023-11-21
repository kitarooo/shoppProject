package com.example.shopp.service;

import com.example.shopp.Enums.Role;
import com.example.shopp.dto.AuthenticationResponse;
import com.example.shopp.dto.request.AuthenticationRequest;
import com.example.shopp.dto.request.RegistrationRequest;
import com.example.shopp.entity.User;
import com.example.shopp.repository.UserRepository;
import com.example.shopp.security.util.JwtService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder encoder;
    private final JwtService service;
    private final AuthenticationManager manager;

    public AuthenticationResponse register(RegistrationRequest request) {
        var user = User.builder()
                .email(request.getEmail())
                .password(encoder.encode(request.getPassword()))
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phoneNumber(request.getPhoneNumber())
                .role(Role.ROLE_ADMIN)
                .build();

        userRepository.save(user);
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        //manager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        Authentication authenticate = manager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );

        //var user = userRepository.findUserByEmail(request.getEmail()).orElseThrow();
        User user = (User) authenticate.getPrincipal();
        userRepository.save(user);
        var jwtToken = service.generateToken(user);
        return AuthenticationResponse.builder()
                .token(jwtToken)
                .build();
    }
}
