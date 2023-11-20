package com.example.shopp.controller.user;

import com.example.shopp.dto.UserDTO;

import com.example.shopp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@Tag(name = "User controller", description = "Uses for logic upon users")
public class UserController {
    //private final UserService service;
    private final UserService userService;

    @PostMapping("/createUser")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "create user", description = "Create default user")
    public ResponseEntity<Object> addUser(@RequestBody UserDTO userDTO) {
        return userService.createUser(userDTO);
    }

    @PutMapping("/updateUserRequest/{id}")
    @PreAuthorize("hasAuthority('USER')")
    @Operation(summary = "update user request", description = "Update email and password for user")
    public ResponseEntity<Object> updateUserRequestById(@PathVariable Long id,
                                                        UserDTO userDTO) {
        return userService.updateUserRequest(id, userDTO);
    }

    @PutMapping("/updateUserInfo/{id}")
    @PreAuthorize("hasAnyAuthority('USER')")
    @Operation(summary = "update user info", operationId = "Update user information, for example: firstname")
    public ResponseEntity<Object> updateUserInfo(@PathVariable Long id, UserDTO userDTO) {
        return userService.updateUserInfoById(id, userDTO);
    }
}
