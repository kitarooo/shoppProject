package com.example.shopp.controller.user;

import com.example.shopp.dto.info.UserInfo;
import com.example.shopp.dto.request.UserRequest;
import com.example.shopp.entity.User;
import com.example.shopp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v2/admin")
public class AdminController {
    private final UserService service;
    @Autowired
    public AdminController(UserService service) {
        this.service = service;
    }


    @GetMapping("/allUsers")
    public List<User> getAll() {
        return service.findAll();
    }

    @PostMapping("/createUserAdmin")
    public ResponseEntity<Object> addUser(UserRequest userRequest, UserInfo userInfo) {
        return service.createUserAdmin(userInfo, userRequest);
    }

    @GetMapping("/findUserWith/{id}")
    public UserInfo getUserById(@PathVariable Long id) {
        return service.getUserById(id);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        return service.deleteUserById(id);
    }
}
