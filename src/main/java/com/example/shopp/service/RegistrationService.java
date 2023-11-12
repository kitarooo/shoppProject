package com.example.shopp.service;


import com.example.shopp.dto.request.UserRequest;
import com.example.shopp.entity.User;
import com.example.shopp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class RegistrationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    @Autowired
    public RegistrationService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public String registration(UserRequest user) {
        Model model = new Model();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.save(mapUserRequestToUser(user));
        model.setResult("User successfully registered!");
        return model.getResult();
    }

    public User mapUserRequestToUser(UserRequest user) {
        return User.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .build();
    }
}
