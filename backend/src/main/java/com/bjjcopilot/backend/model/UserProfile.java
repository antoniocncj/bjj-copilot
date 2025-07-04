package com.bjjcopilot.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "user_profiles")
public class UserProfile {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    
    @NotNull
    @Column(name = "user_id")
    private UUID userId;
    
    @Column(name = "date_of_birth")
    private LocalDate dateOfBirth;
    
    @Size(max = 255)
    private String academy;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "current_belt_id")
    private Belt currentBelt;
    
    @Column(name = "start_date")
    private LocalDate startDate;
    
    @Column(columnDefinition = "TEXT")
    private String preferences;
    
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    
    // Constructors
    public UserProfile() {}
    
    public UserProfile(UUID userId, LocalDate dateOfBirth, String academy, Belt currentBelt, LocalDate startDate) {
        this.userId = userId;
        this.dateOfBirth = dateOfBirth;
        this.academy = academy;
        this.currentBelt = currentBelt;
        this.startDate = startDate;
    }
    
    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    
    public LocalDate getDateOfBirth() { return dateOfBirth; }
    public void setDateOfBirth(LocalDate dateOfBirth) { this.dateOfBirth = dateOfBirth; }
    
    public String getAcademy() { return academy; }
    public void setAcademy(String academy) { this.academy = academy; }
    
    public Belt getCurrentBelt() { return currentBelt; }
    public void setCurrentBelt(Belt currentBelt) { this.currentBelt = currentBelt; }
    
    public LocalDate getStartDate() { return startDate; }
    public void setStartDate(LocalDate startDate) { this.startDate = startDate; }
    
    public String getPreferences() { return preferences; }
    public void setPreferences(String preferences) { this.preferences = preferences; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
}