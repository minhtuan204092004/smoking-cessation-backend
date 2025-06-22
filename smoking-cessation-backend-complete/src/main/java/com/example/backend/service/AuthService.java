package com.example.backend.service;

import com.example.backend.entity.User;
import com.example.backend.security.JwtTokenProvider;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service @RequiredArgsConstructor
public class AuthService {
    private final UserService userService;
    private final JwtTokenProvider provider;

    public String login(String email, String password) {
        var userDetails = (User) userService.loadUserByUsername(email);
        if (!userService.encoder.matches(password, userDetails.getPassword())) {
            throw new RuntimeException("Invalid credentials");
        }
        return provider.generateToken(userDetails.getId());
    }
}