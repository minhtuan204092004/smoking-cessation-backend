package com.example.backend.controller;

import com.example.backend.entity.TreatmentPlan;
import com.example.backend.service.TreatmentPlanService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.*;

@RestController @RequestMapping("/api/plans") @RequiredArgsConstructor
public class TreatmentPlanController {
    private final TreatmentPlanService svc;

    @GetMapping public ResponseEntity<List<TreatmentPlan>> list() {
        return ResponseEntity.ok(svc.listAll());
    }
    @PostMapping public ResponseEntity<TreatmentPlan> create(@RequestBody TreatmentPlan p) {
        return ResponseEntity.ok(svc.create(p));
    }
}