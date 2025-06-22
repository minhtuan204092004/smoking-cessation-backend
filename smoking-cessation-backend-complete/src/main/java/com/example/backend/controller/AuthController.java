package com.example.backend.controller;

import com.example.backend.service.*;
import lombok.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController @RequestMapping("/api/auth") @RequiredArgsConstructor
public class AuthController {
    private final UserService userService;
    private final AuthService authService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody RegisterRequest req) {
        var u = userService.register(req.getUsername(), req.getEmail(), req.getPassword());
        return ResponseEntity.ok(u);
    }
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequest req) {
        String token = authService.login(req.getEmail(), req.getPassword());
        return ResponseEntity.ok(new AuthResponse(token));
    }
}

@Data class RegisterRequest { private String username, email, password; }
@Data class AuthRequest { private String email, password; }
@Data @AllArgsConstructor class AuthResponse { private String token; }