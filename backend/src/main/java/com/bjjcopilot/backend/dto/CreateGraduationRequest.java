package com.bjjcopilot.backend.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.util.UUID;

public class CreateGraduationRequest {
    
    @NotNull
    private UUID userId;
    
    private UUID fromBeltId;
    
    @NotNull
    private UUID toBeltId;
    
    @NotNull
    private LocalDate graduationDate;
    
    private String notes;
    
    // Constructors
    public CreateGraduationRequest() {}
    
    public CreateGraduationRequest(UUID userId, UUID fromBeltId, UUID toBeltId, 
                                  LocalDate graduationDate, String notes) {
        this.userId = userId;
        this.fromBeltId = fromBeltId;
        this.toBeltId = toBeltId;
        this.graduationDate = graduationDate;
        this.notes = notes;
    }
    
    // Getters and Setters
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    
    public UUID getFromBeltId() { return fromBeltId; }
    public void setFromBeltId(UUID fromBeltId) { this.fromBeltId = fromBeltId; }
    
    public UUID getToBeltId() { return toBeltId; }
    public void setToBeltId(UUID toBeltId) { this.toBeltId = toBeltId; }
    
    public LocalDate getGraduationDate() { return graduationDate; }
    public void setGraduationDate(LocalDate graduationDate) { this.graduationDate = graduationDate; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
}