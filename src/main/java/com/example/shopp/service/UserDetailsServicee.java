package com.example.shopp.service;

import com.example.shopp.entity.User;
import com.example.shopp.exception.NotFoundException;
import com.example.shopp.repository.UserRepository;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.logging.Logger;

@Service
public class UserDetailsServicee implements UserDetailsService {
    private final UserRepository userRepository;
    @Autowired
    public UserDetailsServicee(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /*@Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findUserByEmail(email);

        if(user.isEmpty()) {
            throw new UsernameNotFoundException("User not found!");
        }

        return new User(user.get()) {
        };
    }*/

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        User user = userRepository.findUserByEmail(email).orElseThrow(
                () -> new NotFoundException("User not found"));

        return new org.springframework.security.core.userdetails.User(user.getEmail(),
                user.getPassword(), user.getAuthorities());
    }
}
