package com.example.backend.controller;

import com.example.backend.entity.Product;
import com.example.backend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/products") @RequiredArgsConstructor
public class ProductController {
    private final ProductService svc;

    @GetMapping public ResponseEntity<List<Product>> list() {
        return ResponseEntity.ok(svc.listAll());
    }
    @PostMapping public ResponseEntity<Product> create(@RequestBody Product p) {
        return ResponseEntity.ok(svc.create(p));
    }
}