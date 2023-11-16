package com.example.shopp.service;

import com.example.shopp.dto.Enums.Role;
import com.example.shopp.dto.info.UserInfo;
import com.example.shopp.dto.request.UserRequest;
import com.example.shopp.entity.User;
import com.example.shopp.exception.NotFoundException;
import com.example.shopp.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserService {
    private final UserRepository userRepository;
    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User checkUserOnExistAndReturn(Long userId) {
        return userRepository.findAllByUserId(userId).orElseThrow( () -> new NotFoundException("User was not found"));
    }


    //ADMIN
    public ResponseEntity<Object> createUserAdmin(UserInfo userInfo, UserRequest userRequest) {
        Model model = new Model();

        if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) {
            model.setResult("User with email:" + userRequest.getEmail() + " already exist!");
            return ResponseEntity.ok(model.getResult());
        }

        userRepository.save(
                User.builder()
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .userInfo(UserInfo.builder()
                                .firstName(userInfo.getFirstName())
                                .lastName(userInfo.getLastName())
                                .phoneNumber(userInfo.getPhoneNumber())
                                .build())
                        .role(Role.ADMIN)
                        .build());
        model.setResult("User successfully established");
        return ResponseEntity.ok(model.getResult());
    }


    //USER
    public ResponseEntity<Object> createUser(UserInfo userInfo , UserRequest userRequest) {
        Model model = new Model();

        if (userRepository.findUserByEmail(userRequest.getEmail()).isPresent()) {
            model.setResult("User with email:"  + userRequest.getEmail() +  " already exist!");
            return ResponseEntity.ok(model.getResult());
        }

        userRepository.save(
                User.builder()
                        .email(userRequest.getEmail())
                        .password(userRequest.getPassword())
                        .userInfo(UserInfo.builder()
                                .firstName(userInfo.getFirstName())
                                .lastName(userInfo.getLastName())
                                .phoneNumber(userInfo.getPhoneNumber())
                                .build())
                        .role(Role.USER)
                        .build());
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