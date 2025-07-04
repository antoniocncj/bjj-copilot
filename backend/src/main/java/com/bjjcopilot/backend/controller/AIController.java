package com.bjjcopilot.backend.controller;

import com.bjjcopilot.backend.dto.ChatRequest;
import com.bjjcopilot.backend.dto.ChatResponse;
import com.bjjcopilot.backend.service.AIService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/api/ai")
public class AIController {
    
    private final AIService aiService;
    
    @Autowired
    public AIController(AIService aiService) {
        this.aiService = aiService;
    }
    
    @PostMapping("/chat")
    public ResponseEntity<ChatResponse> chat(
            @Valid @RequestBody ChatRequest request,
            @RequestHeader("X-User-Id") UUID userId) {
        try {
            ChatResponse response = aiService.processChatMessage(request, userId);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}