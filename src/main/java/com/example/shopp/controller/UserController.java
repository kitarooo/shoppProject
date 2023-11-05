package com.example.shopp.controller;

import com.example.shopp.dto.UserInfo;
import com.example.shopp.dto.UserRequest;
import com.example.shopp.entity.User;
import com.example.shopp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @GetMapping("/allUsers")
    public List<User> getAll() {
        return service.findAll();
    }

    @PostMapping("/createUser")
    public ResponseEntity<Object> addUser(UserRequest userRequest,UserInfo userInfo) {
        return service.createUser(userInfo, userRequest);
    }

    @GetMapping("/findUserWith/{id}")
    public UserInfo getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @PutMapping("/updateUserRequest/{id}")
    public ResponseEntity<Object> updateUserRequestById(@PathVariable Long id,
                                                        UserRequest userRequest) {
        return service.updateUserRequest(userRequest, id);
    }
    @PutMapping("/updateUserInfo/{id}")
    public ResponseEntity<Object> updateUserInfo(@PathVariable Long id,UserInfo userInfo) {
        return service.updateUserInfoById(id, userInfo);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        return service.deleteUserById(id);
    }
}
