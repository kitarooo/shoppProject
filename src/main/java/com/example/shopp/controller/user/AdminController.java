package com.example.shopp.controller.user;

import com.example.shopp.dto.UserDTO;
import com.example.shopp.entity.User;
import com.example.shopp.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/admin")
@RequiredArgsConstructor
@Tag(name = "Admin controller", description = "Controller for ADMIN")
public class AdminController {
    //private final UserService service;
    private final UserService userService;

    @GetMapping("/allUsers")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "for get all users", description = "For get list users for ADMIN")
    public List<User> getAll() {
        return userService.findAll();
    }

    @PostMapping("/createUserAdmin")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "add admin", description = "For add user admin")
    public ResponseEntity<Object> addUser(@RequestBody UserDTO userDTO) {
        return userService.createUserAdmin(userDTO);
    }

    @GetMapping("{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    @Operation(summary = "get user", description = "For get user by id")
    public UserDTO getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "delete user", description = "Delete user by id")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        return userService.deleteUserById(id);
    }
}
