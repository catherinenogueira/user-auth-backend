package com.gameflix.user_auth.service;

import com.gameflix.user_auth.model.User;
import com.gameflix.user_auth.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public String registerUser(String username, String password) {
        if (userRepository.existsByUsername(username)) {
            return "Username already exists";
        }
        String hashedPassword = passwordEncoder.encode(password);
        userRepository.save(new User(username, hashedPassword));
        return "User registered successfully";
    }

    public String loginUser(String username, String password) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user != null && passwordEncoder.matches(password, user.getPassword())) {
            return "Login successful";
        }
        return "Invalid username or password";
    }
}
