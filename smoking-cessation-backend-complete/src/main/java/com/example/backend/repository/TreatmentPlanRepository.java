package com.example.backend.repository;

import com.example.backend.entity.TreatmentPlan;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TreatmentPlanRepository extends JpaRepository<TreatmentPlan, Long> { }