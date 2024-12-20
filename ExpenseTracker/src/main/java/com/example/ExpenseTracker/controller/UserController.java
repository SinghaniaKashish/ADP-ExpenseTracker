package com.example.ExpenseTracker.controller;

import com.example.ExpenseTracker.model.UserDetails;
import com.example.ExpenseTracker.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;


@RestController
@RequestMapping("/api/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public UserDetails register(@RequestBody UserDetails user) {
        return userService.registerUser(user);
    }

    // @PostMapping("/login")
    // public ResponseEntity<?> login(@RequestParam String email, @RequestParam String password) {
    //     Optional<UserDetails> user = userService.login(email, password);
    //     if (user.isPresent()) {
    //         return ResponseEntity.ok(user.get());
    //     } else {
    //         return ResponseEntity.status(401).body("Invalid email or password");
    //     }
    // }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody Map<String, String> credentials) {
        String email = credentials.get("email");
        String password = credentials.get("password");
        Optional<UserDetails> user = userService.login(email, password);
        
        if (user.isPresent()) {
            return ResponseEntity.ok(user.get());
        } else {
            return ResponseEntity.status(401).body("Invalid email or password");
        }
    }

}
