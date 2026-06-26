package com.example.dashboard.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dashboard.entity.User;
import com.example.dashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
public class UserService {
	public final UserRepository userRepository;
	private final PasswordEncoder passwordEncoder;
	
	public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
		this.userRepository = userRepository;
		this.passwordEncoder = passwordEncoder;
	}
	
	public User register(User user) {

	    if (userRepository.findByEmail(user.getEmail()).isPresent()) {

	        throw new RuntimeException("Email already exists.");

	    }

	    user.setPasswordHash(
	        passwordEncoder.encode(user.getPasswordHash())
	    );

	    user.setRole("USER");

	    return userRepository.save(user);

	}
	
	public User findByEmail(String email) {
		return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
	}
	
	public boolean checkPassword(User user, String rawPassword) {
		return passwordEncoder.matches(rawPassword, user.getPasswordHash());
	}
}
