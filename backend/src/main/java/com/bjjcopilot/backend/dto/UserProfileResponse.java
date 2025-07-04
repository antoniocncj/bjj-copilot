package com.bjjcopilot.backend.dto;

import java.time.LocalDate;
import java.util.UUID;

public class UserProfileResponse {
    
    private UUID id;
    private LocalDate dateOfBirth;
    private String academy;
    private BeltResponse currentBelt;
    private LocalDate startDate;
    private String preferences;
    
    // Constructors
    public UserProfileResponse() {}
    
    public UserProfileResponse(UUID id, LocalDate dateOfBirth, String academy, 
                              BeltResponse currentBelt, LocalDate startDate, String preferences) {
        this.id = id;
        this.dateOfBirth = dateOfBirth;
        this.academy = academy;
        this.currentBelt = currentBelt;
        this.startDate = startDate;
        this.preferences = preferences;
    }
    
    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getAcademy() { return academy; }
    public void setAcademy(String academy) { this.academy = academy; }
    
    public BeltResponse getCurrentBelt() { return currentBelt; }
    public void setCurrentBelt(BeltResponse currentBelt) { this.currentBelt = currentBelt; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
}