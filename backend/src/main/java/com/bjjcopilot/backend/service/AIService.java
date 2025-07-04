package com.bjjcopilot.backend.service;

import com.bjjcopilot.backend.dto.ChatRequest;
import com.bjjcopilot.backend.dto.ChatResponse;
import com.bjjcopilot.backend.dto.UserResponse;
import com.bjjcopilot.backend.model.UserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AIService {
    
    private final UserService userService;
    
    @Value("${azure.openai.endpoint:}")
    private String openaiEndpoint;
    
    @Value("${azure.openai.key:}")
    private String openaiKey;
    
    @Value("${azure.openai.deployment-name:gpt-4}")
    private String deploymentName;
    
    @Autowired
    public AIService(UserService userService) {
        this.userService = userService;
    }
    
    public ChatResponse processChatMessage(ChatRequest request, UUID userId) {
        // Get user context
        UserResponse user = userService.getUserById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));
        
        // Build context-aware prompt
        String contextualizedPrompt = buildContextualizedPrompt(request.getMessage(), user);
        
        // For now, return a mock response
        // TODO: Implement actual Azure OpenAI integration
        String mockResponse = generateMockResponse(contextualizedPrompt, user);
        
        return new ChatResponse(
                mockResponse,
                request.getConversationId() != null ? request.getConversationId() : UUID.randomUUID().toString(),
                System.currentTimeMillis()
        );
    }
    
    private String buildContextualizedPrompt(String userMessage, UserResponse user) {
        StringBuilder prompt = new StringBuilder();
        prompt.append("You are a Brazilian Jiu-Jitsu (BJJ) AI assistant. ");
        prompt.append("You are helping a ").append(user.getRole().toString().toLowerCase()).append(" named ").append(user.getName()).append(". ");
        
        if (user.getProfile() != null && user.getProfile().getCurrentBelt() != null) {
            prompt.append("They are currently a ").append(user.getProfile().getCurrentBelt().getDisplayName()).append(" belt. ");
            
            if (user.getProfile().getAcademy() != null) {
                prompt.append("They train at ").append(user.getProfile().getAcademy()).append(". ");
            }
        }
        
        prompt.append("Please provide helpful, accurate BJJ advice and information. ");
        prompt.append("User's question: ").append(userMessage);
        
        return prompt.toString();
    }
    
    private String generateMockResponse(String contextualizedPrompt, UserResponse user) {
        // This is a mock response for now
        // In a real implementation, this would call Azure OpenAI
        
        if (contextualizedPrompt.toLowerCase().contains("guard")) {
            return "The guard is one of the fundamental positions in BJJ. From guard, you have many options for sweeps, submissions, and transitions. " +
                   "Some basic techniques from guard include the armbar, triangle choke, and scissor sweep. " +
                   "As a " + (user.getProfile() != null && user.getProfile().getCurrentBelt() != null ? 
                   user.getProfile().getCurrentBelt().getDisplayName() : "practitioner") + 
                   ", focus on maintaining good posture and being patient with your attacks.";
        } else if (contextualizedPrompt.toLowerCase().contains("mount")) {
            return "Mount is considered one of the most dominant positions in BJJ. From mount, you have excellent control over your opponent " +
                   "and many submission opportunities including armbars, cross chokes, and ezekiel chokes. " +
                   "Focus on maintaining good base and controlling your opponent's arms.";
        } else if (contextualizedPrompt.toLowerCase().contains("submission")) {
            return "Submissions are the ultimate goal in BJJ. Remember that technique and timing are more important than strength. " +
                   "Always prioritize control and position before attempting submissions. " +
                   "Practice your fundamental submissions like armbars, triangles, and rear naked chokes until they become second nature.";
        } else {
            return "Thank you for your question about BJJ! I'm here to help you improve your Brazilian Jiu-Jitsu knowledge and skills. " +
                   "Feel free to ask me about techniques, positions, strategy, or any other aspect of BJJ training. " +
                   "Remember, consistency and patience are key to progress in the gentle art!";
        }
    }
}