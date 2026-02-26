package com.example.dashboard.service;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.dashboard.entity.User;
import com.example.dashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User register(User user) {
		user.setPasswordHash(passwordEncoder.encode(user.getPasswordHash()));
		user.setRole("USER");
		return userRepository.save(user);
	}
	
	public boolean checkPassword(User user, String rawPassword) {
		return passwordEncoder.matches(rawPassword, user.getPasswordHash());
	}
}
