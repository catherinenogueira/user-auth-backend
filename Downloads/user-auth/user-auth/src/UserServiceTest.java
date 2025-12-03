package com.gameflix.user_auth;

import com.gameflix.user_auth.model.User;
import com.gameflix.user_auth.repository.UserRepository;
import com.gameflix.user_auth.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.Optional;

import static org.mockito.Mockito.*;

@SpringBootTest
class UserServiceTest {

    @Autowired
    private UserService userService;

    @MockBean
    private UserRepository userRepository;

    @Test
    void registerUser_ShouldReturnSuccessMessage() {
        when(userRepository.existsByUsername("newUser")).thenReturn(false);

        String result = userService.registerUser("newUser", "password123");

        Assertions.assertEquals("User registered successfully", result);
    }

    @Test
    void registerUser_ShouldDetectDuplicateUsername() {
        when(userRepository.existsByUsername("existingUser")).thenReturn(true);

        String result = userService.registerUser("existingUser", "password123");

        Assertions.assertEquals("Username already exists", result);
    }

    @Test
    void loginUser_ShouldReturnSuccess() {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashed = encoder.encode("mypassword");

        User mockUser = new User("testUser", hashed);

        when(userRepository.findByUsername("testUser"))
                .thenReturn(Optional.of(mockUser));

        String result = userService.loginUser("testUser", "mypassword");

        Assertions.assertEquals("Login successful", result);
    }
}
