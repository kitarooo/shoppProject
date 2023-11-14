package com.example.shopp.controller;

import com.example.shopp.dto.UserDTO;
import com.example.shopp.dto.info.UserInfo;
import com.example.shopp.dto.request.UserRequest;
import com.example.shopp.entity.User;
import com.example.shopp.repository.UserRepository;
import com.example.shopp.service.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/auth")
public class AuthController {
    private final UserRepository userRepository;
    private final RegistrationService registrationService;
    @Autowired
    public AuthController(UserRepository userRepository, RegistrationService registrationService) {
        this.userRepository = userRepository;
        this.registrationService = registrationService;
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }
    @GetMapping("/registration")
    public String registrationPage(@ModelAttribute("user") User user){
        return "registration";
    }

    @PostMapping("/registration")
    public String performRegistration(@ModelAttribute("userDTO") UserRequest user) {
        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            return "registration";
        }
        registrationService.registration(user);
        return "login";
    }
}
