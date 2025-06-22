package com.example.backend.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="treatment_plans")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TreatmentPlan {
    @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
    private String name;
    @Lob private String description;
    private int durationDays;
}