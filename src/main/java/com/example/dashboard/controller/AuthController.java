package com.example.dashboard.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.dashboard.entity.User;
import com.example.dashboard.service.UserService;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
	
	private final UserService userService;
	
	public AuthController(UserService userService) {
		this.userService = userService;
	}
	
	
	@PostMapping("/register")
	public User register(@RequestBody User user) {
		return userService.register(user);
	}
	
	@PostMapping("/login")
	public String login(@RequestBody User loginRequest) {
	    User user = userService.userRepository.findByEmail(loginRequest.getEmail())
	            .orElseThrow(() -> new RuntimeException("Invalid credentials"));

	    boolean valid = userService.checkPassword(user, loginRequest.getPasswordHash());
	    if (!valid) {
	        throw new RuntimeException("Invalid credentials");
	    }

	    // For now, just return success text; later you can return JWT or session info
	    return "Login successful for user: " + user.getEmail();
	}
}
