package com.example.dashboard;

import java.time.LocalDateTime;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.example.dashboard.entity.User;
import com.example.dashboard.repository.UserRepository;

@SpringBootApplication
public class DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(DashboardApplication.class, args);
	}
	@Bean
	@Profile("!test")
    CommandLineRunner init(UserRepository userRepository,
                           PasswordEncoder encoder) {
        return args -> {
            if (userRepository.findByEmail("erfan@example.com").isEmpty()) {

                User user = new User();
                user.setName("Erfan");
                user.setEmail("erfan@example.com");
                user.setPasswordHash(encoder.encode("password"));
                user.setRole("USER");
                user.setCreatedAt(LocalDateTime.now());

                userRepository.save(user);
                System.out.println("Test user created.");
            }
        };
    }
}
