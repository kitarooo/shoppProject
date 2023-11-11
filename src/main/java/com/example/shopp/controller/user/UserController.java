package com.example.shopp.controller.user;

import com.example.shopp.dto.info.UserInfo;
import com.example.shopp.dto.request.UserRequest;
import com.example.shopp.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService service;

    @PostMapping("/createUser")
    public ResponseEntity<Object> addUser(UserRequest userRequest,UserInfo userInfo) {
        return service.createUser(userInfo, userRequest);
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
}
