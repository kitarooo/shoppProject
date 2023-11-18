package com.example.shopp.service;

import com.example.shopp.entity.User;
import com.example.shopp.repository.UserRepository;
import com.example.shopp.security.UserDetailss;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServicee implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServicee(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new UserDetailss(user.get());
    }
}
