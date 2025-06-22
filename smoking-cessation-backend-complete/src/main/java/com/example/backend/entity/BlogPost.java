package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.Instant;

@Entity
@Table(name="blog_posts")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class BlogPost {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String title;
    @Lob private String content;
    private Instant createdAt = Instant.now();
    @ManyToOne @JoinColumn(name="author_id") private User author;
}