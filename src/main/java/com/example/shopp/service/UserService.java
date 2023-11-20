package com.example.shopp.service;

import com.example.shopp.dto.Enums.Role;
import com.example.shopp.dto.UserDTO;
import com.example.shopp.entity.User;
import com.example.shopp.exception.NotFoundException;
import com.example.shopp.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;


    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User checkUserOnExistAndReturn(Long userId) {
        return userRepository.findAllByUserId(userId).orElseThrow( () -> new NotFoundException("User was not found"));
    }

    public ResponseEntity<Object> createUser(UserDTO userDTO) {
        Model model = new Model();

        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()) {
            model.setResult("User with email:"  + userDTO.getEmail() +  " already exist!");
            return ResponseEntity.ok(model.getResult());
        }

        userRepository.save(User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(Role.USER)
                .build());
        model.setResult("User successfully established");
        return ResponseEntity.ok(model.getResult());
    }

    public ResponseEntity<Object> createUserAdmin(UserDTO userDTO) {
        Model model = new Model();

        if (userRepository.findUserByEmail(userDTO.getEmail()).isPresent()) {
            model.setResult("User with email:"  + userDTO.getEmail() +  " already exist!");
            return ResponseEntity.ok(model.getResult());
        }

        userRepository.save(User.builder()
                .email(userDTO.getEmail())
                .password(userDTO.getPassword())
                .firstName(userDTO.getFirstName())
                .lastName(userDTO.getLastName())
                .phoneNumber(userDTO.getPhoneNumber())
                .role(Role.ADMIN)
                .build());
        model.setResult("User successfully established");
        return ResponseEntity.ok(model.getResult());
    }

    public UserDTO getUserById(Long id) {
        //add if, exception
        User user = checkUserOnExistAndReturn(id);
        return UserDTO.builder()
                .email(user.getEmail())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .phoneNumber(user.getPhoneNumber())
                .build();
    }

    public ResponseEntity<Object> updateUserInfoById(Long id, UserDTO userDTO) {
        User user = checkUserOnExistAndReturn(id);
        Model model = new Model();
        user.setFirstName(userDTO.getFirstName());
        user.setLastName(userDTO.getLastName());
        user.setPhoneNumber(userDTO.getPhoneNumber());
        userRepository.save(user);
        model.setResult("User info was updated");

        return ResponseEntity.ok(model.getResult());
    }

    public ResponseEntity<Object> updateUserRequest(Long id, UserDTO userDTO) {
        User user = checkUserOnExistAndReturn(id);
        Model model = new Model();
        user.setEmail(userDTO.getEmail());
        user.setPassword(userDTO.getPassword());
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
