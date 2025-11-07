package com.gameflix.user_auth.controller;

import com.gameflix.user_auth.service.UserService;
import org.springframework.web.bind.annotation.*;
import java.util.Map;

@RestController
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/register")
    public Map<String, String> register(@RequestBody Map<String, String> request) {
        String message = userService.registerUser(request.get("username"), request.get("password"));
        return Map.of("message", message);
    }

    @PostMapping("/login")
    public Map<String, String> login(@RequestBody Map<String, String> request) {
        String message = userService.loginUser(request.get("username"), request.get("password"));
        return Map.of("message", message);
    }
}
