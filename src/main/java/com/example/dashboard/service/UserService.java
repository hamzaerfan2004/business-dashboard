package com.example.dashboard.service;

import org.springframework.stereotype.Service;

import com.example.dashboard.entity.User;
import com.example.dashboard.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	private final UserRepository userRepository;
	
	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User register(User user) {
		user.setRole("USER");
		return userRepository.save(user);
	}
}
