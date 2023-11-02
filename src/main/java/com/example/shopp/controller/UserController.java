package com.example.shopp.controller;

import com.example.shopp.model.User;
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
    public List<User> findAll(){
        return service.findAll();
    }

    @PostMapping("/create_user")
    public ResponseEntity<Object> createUser(User user) {
        Model model = new Model();
        service.createUser(user);
        model.setStatus(200);
        model.setResult("User was add!");
        return ResponseEntity.ok(model.getResult());
    }
    @GetMapping("{id}")
    public ResponseEntity<Object> findUserById(@PathVariable Long id) {
        Model model = new Model();
        User user = service.findUserById(id);

        if (user != null) {
            return ResponseEntity.ok(user);
        } else {
            model.setResult("User was not found");
            return ResponseEntity.ok(model.getResult());
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<Object> updateUserById(@PathVariable Long id, String email) {
        Model model = new Model();
        service.updateUserById(id,email);
        model.setResult("User was update");
        model.setStatus(200);
        return ResponseEntity.ok(model.getResult());
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        service.deleteUserById(id);
        Model model = new Model();
        model.setStatus(200);
        model.setResult("User was deleted");
        return ResponseEntity.ok(model.getResult());
    }
}
