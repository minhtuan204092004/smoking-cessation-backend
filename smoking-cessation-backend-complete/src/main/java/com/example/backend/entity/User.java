package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.*;
import java.util.stream.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
public User register(String username, String email, String password) {
    if (userRepo.existsByEmail(email)) {
        throw new RuntimeException("Email exists");
    }
    User u = new User();
    u.setUsername(username);
    u.setEmail(email);
    u.setPassword(encoder.encode(password));
    Role userRole = roleRepo.findByName(ERole.ROLE_USER)
        .orElseThrow(() -> new RuntimeException("Role not found"));
    u.getRoles().add(userRole);
    return userRepo.save(u);
}

@Entity
@Table(name="users")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements UserDetails {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    @Column(unique=true) private String email;
    private String password;
    private String username;

    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(name="user_roles",
      joinColumns=@JoinColumn(name="user_id"),
      inverseJoinColumns=@JoinColumn(name="role_id"))
    private Set<Role> roles = new HashSet<>();

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return roles.stream()
            .map(r -> new SimpleGrantedAuthority(r.getName().name()))
            .collect(Collectors.toList());
    }
    @Override public String getPassword() { return password; }
    @Override public String getUsername() { return email; }
    @Override public boolean isAccountNonExpired() { return true; }
    @Override public boolean isAccountNonLocked() { return true; }
    @Override public boolean isCredentialsNonExpired() { return true; }
    @Override public boolean isEnabled() { return true; }
}