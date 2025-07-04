package com.bjjcopilot.backend.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

public class GraduationHistoryResponse {
    
    private UUID id;
    private UUID userId;
    private String userName;
    private BeltResponse fromBelt;
    private BeltResponse toBelt;
    private LocalDate graduationDate;
    private UUID instructorId;
    private String instructorName;
    private String notes;
    private LocalDateTime createdAt;
    
    // Constructors
    public GraduationHistoryResponse() {}
    
    public GraduationHistoryResponse(UUID id, UUID userId, String userName, BeltResponse fromBelt, 
                                   BeltResponse toBelt, LocalDate graduationDate, UUID instructorId, 
                                   String instructorName, String notes, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.fromBelt = fromBelt;
        this.toBelt = toBelt;
        this.graduationDate = graduationDate;
        this.instructorId = instructorId;
        this.instructorName = instructorName;
        this.notes = notes;
        this.createdAt = createdAt;
    }
    
    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    
    public String getUserName() { return userName; }
    public void setUserName(String userName) { this.userName = userName; }
    
    public BeltResponse getFromBelt() { return fromBelt; }
    public void setFromBelt(BeltResponse fromBelt) { this.fromBelt = fromBelt; }
    
    public BeltResponse getToBelt() { return toBelt; }
    public void setToBelt(BeltResponse toBelt) { this.toBelt = toBelt; }
    
    public LocalDate getGraduationDate() { return graduationDate; }
    public void setGraduationDate(LocalDate graduationDate) { this.graduationDate = graduationDate; }
    
    public UUID getInstructorId() { return instructorId; }
    public void setInstructorId(UUID instructorId) { this.instructorId = instructorId; }
    
    public String getInstructorName() { return instructorName; }
    public void setInstructorName(String instructorName) { this.instructorName = instructorName; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}