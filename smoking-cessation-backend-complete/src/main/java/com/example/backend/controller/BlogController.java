package com.example.backend.controller;

import com.example.backend.entity.BlogPost;
import com.example.backend.service.BlogPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.*;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.util.*;

@RestController @RequestMapping("/api/blog") @RequiredArgsConstructor
public class BlogController {
    private final BlogPostService svc;

    @GetMapping public ResponseEntity<List<BlogPost>> list() {
        return ResponseEntity.ok(svc.listAll());
    }

    @PostMapping public ResponseEntity<BlogPost> create(
        @RequestBody Map<String,String> body,
        @AuthenticationPrincipal Principal p) {
        BlogPost post = svc.create(body.get("title"), body.get("content"), Long.parseLong(p.getName()));
        return ResponseEntity.ok(post);
    }
}