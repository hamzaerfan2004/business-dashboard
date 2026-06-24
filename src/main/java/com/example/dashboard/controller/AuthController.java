package com.example.dashboard.controller;

import com.example.dashboard.dto.LoginRequest;
import com.example.dashboard.dto.LoginResponse;
import com.example.dashboard.dto.RegisterRequest;
import com.example.dashboard.entity.User;
import com.example.dashboard.security.JwtService;
import com.example.dashboard.service.UserService;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.CrossOrigin;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    private final JwtService jwtService;

    public AuthController(UserService userService,
                          JwtService jwtService) {

        this.userService = userService;

        this.jwtService = jwtService;

    }

    @PostMapping("/register")
    public User register(@RequestBody RegisterRequest request) {

        User user = new User();

        user.setName(request.getName());
        user.setEmail(request.getEmail());

        // Temporarily store the plain password here.
        // UserService will hash it before saving.
        user.setPasswordHash(request.getPassword());

        return userService.register(user);
    }

    @PostMapping("/login")
    public LoginResponse login(@RequestBody LoginRequest request) {

        User user = userService.findByEmail(request.getEmail());

        if (!userService.checkPassword(user, request.getPassword())) {

            throw new RuntimeException("Invalid credentials");

        }

        String token = jwtService.generateToken(user.getEmail());

        return new LoginResponse(token);

    }

}