package com.bjjcopilot.backend.dto;

public class ChatResponse {
    
    private String message;
    private String conversationId;
    private long timestamp;
    
    // Constructors
    public ChatResponse() {}
    
    public ChatResponse(String message, String conversationId, long timestamp) {
        this.message = message;
        this.conversationId = conversationId;
        this.timestamp = timestamp;
    }
    
    // Getters and Setters
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    
    public String getConversationId() { return conversationId; }
    public void setConversationId(String conversationId) { this.conversationId = conversationId; }
    
    public long getTimestamp() { return timestamp; }
    public void setTimestamp(long timestamp) { this.timestamp = timestamp; }
}