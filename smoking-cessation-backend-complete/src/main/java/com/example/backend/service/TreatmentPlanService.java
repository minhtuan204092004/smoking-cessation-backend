package com.example.backend.service;

import com.example.backend.entity.*;
import com.example.backend.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.*;

@Service @RequiredArgsConstructor
public class TreatmentPlanService {
    private final TreatmentPlanRepository repo;
    public List<TreatmentPlan> listAll() { return repo.findAll(); }
    public TreatmentPlan create(TreatmentPlan plan) { return repo.save(plan); }
}