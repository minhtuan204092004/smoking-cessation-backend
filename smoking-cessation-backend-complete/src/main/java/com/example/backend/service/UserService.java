package com.example.backend.service;

import com.example.backend.entity.*;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.*;

@Service @RequiredArgsConstructor
public class UserService implements UserDetailsService {
    private final UserRepository userRepo;
    private final RoleRepository roleRepo;
    private final PasswordEncoder encoder;

    public User register(String username, String email, String password) {
        if (userRepo.existsByEmail(email)) throw new RuntimeException("Email exists");
        User u = new User();
        u.setUsername(username);
        u.setEmail(email);
        u.setPassword(encoder.encode(password));
        Role userRole = roleRepo.findByName(ERole.ROLE_USER)
            .orElseThrow(() -> new RuntimeException("Role not found"));
        u.getRoles().add(userRole);
        return userRepo.save(u);
    }
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
    public UserDetails loadUserById(Long id) {
        return userRepo.findById(id).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}