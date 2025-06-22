package com.example.backend.service;

import com.example.backend.entity.*;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service @RequiredArgsConstructor
public class ProductService {
    private final ProductRepository repo;
    public List<Product> listAll() { return repo.findAll(); }
    public Product create(Product p) { return repo.save(p); }
}