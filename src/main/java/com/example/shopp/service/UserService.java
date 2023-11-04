package com.example.shopp.service;

import com.example.shopp.dto.UserRequest;
import com.example.shopp.entity.User;
import com.example.shopp.entity.UserInfo;
import com.example.shopp.exception.NotFoundException;
import com.example.shopp.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User checkUserOnExistAndReturn(Long id) {
        return userRepository.findAllById(id).orElseThrow( () -> new NotFoundException("User was not found"));
    }

    public ResponseEntity<Object> createUser(User user) {
        Model model = new Model();

        if (userRepository.findUserByEmail(user.getEmail()).isPresent()) {
            model.setResult("User with email: + user.getEmail() +  already exist!");
            return ResponseEntity.ok(model.getResult());
        }

        userRepository.save(user);
        model.setResult("User successfully established");
        return ResponseEntity.ok(model.getResult());
    }

    public UserInfo getUserById(Long id) {
        //add if, exception
       User user = checkUserOnExistAndReturn(id);
       return user.getUserInfo();
    }

    public ResponseEntity<Object> updateUserInfoById(Long id, UserInfo userInfo) {
        User user = checkUserOnExistAndReturn(id);
        Model model = new Model();

        user.setUserInfo(userInfo);
        userRepository.save(user);
        model.setResult("User info was updated");

        return ResponseEntity.ok(model.getResult());
    }

    public ResponseEntity<Object> updateUserRequest(UserRequest userRequest, Long id) {
        User user = checkUserOnExistAndReturn(id);
        Model model = new Model();

        user.setPassword(userRequest.getPassword());
        userRepository.save(user);
        model.setResult("User password was updated!");

        return ResponseEntity.ok(model.getResult());
    }

    public ResponseEntity<Object> deleteUserById(Long id){
        Model model = new Model();
        if (userRepository.findById(id).isEmpty()) {
            model.setResult("User not deleted, because user not found!");
            return ResponseEntity.ok(model.getResult());
        }
        userRepository.deleteById(id);
        model.setResult("User was deleted!");

        return ResponseEntity.ok(model.getResult());
    }
}