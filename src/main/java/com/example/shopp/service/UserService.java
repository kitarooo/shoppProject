package com.example.shopp.service;

import com.example.shopp.model.User;
import com.example.shopp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        userRepository.save(user);
        return user;
    }

    public User findUserById(Long id) {
        return userRepository.findAllById(id).orElse(null);
    }

    public User updateUserById(Long id, String email) {
        User user = userRepository.findAllById(id).orElse(null);
        if (user != null) {
            user.setEmail(email);
            userRepository.save(user);
        }
        return user;
    }

    public void deleteUserById(Long id){
        userRepository.deleteById(id);
        ResponseEntity.ok("User was deleted");
    }
}