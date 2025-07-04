package com.bjjcopilot.backend.controller;

import com.bjjcopilot.backend.dto.CreateGraduationRequest;
import com.bjjcopilot.backend.dto.GraduationHistoryResponse;
import com.bjjcopilot.backend.service.GraduationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/graduations")
public class GraduationController {
    
    private final GraduationService graduationService;
    
    @Autowired
    public GraduationController(GraduationService graduationService) {
        this.graduationService = graduationService;
    }
    
    @PostMapping
    public ResponseEntity<GraduationHistoryResponse> createGraduation(
            @Valid @RequestBody CreateGraduationRequest request,
            @RequestHeader("X-Instructor-Id") UUID instructorId) {
        try {
            GraduationHistoryResponse response = graduationService.createGraduation(request, instructorId);
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
    
    @GetMapping
    public ResponseEntity<List<GraduationHistoryResponse>> getAllGraduations() {
        List<GraduationHistoryResponse> graduations = graduationService.getAllGraduations();
        return ResponseEntity.ok(graduations);
    }
    
    @GetMapping("/user/{userId}")
    public ResponseEntity<List<GraduationHistoryResponse>> getGraduationsByUser(@PathVariable UUID userId) {
        try {
            List<GraduationHistoryResponse> graduations = graduationService.getGraduationHistoryByUser(userId);
            return ResponseEntity.ok(graduations);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/instructor/{instructorId}")
    public ResponseEntity<List<GraduationHistoryResponse>> getGraduationsByInstructor(@PathVariable UUID instructorId) {
        List<GraduationHistoryResponse> graduations = graduationService.getGraduationsByInstructor(instructorId);
        return ResponseEntity.ok(graduations);
    }
}