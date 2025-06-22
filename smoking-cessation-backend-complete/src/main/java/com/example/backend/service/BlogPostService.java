package com.example.backend.service;

import com.example.backend.entity.*;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service @RequiredArgsConstructor
public class BlogPostService {
    private final BlogPostRepository repo;
    private final UserService userService;

    public List<BlogPost> listAll() { return repo.findAll(); }

    public BlogPost create(String title, String content, Long userId) {
        User u = (User) userService.loadUserById(userId);
        BlogPost post = new BlogPost();
        post.setTitle(title); post.setContent(content); post.setAuthor(u);
        return repo.save(post);
    }
}