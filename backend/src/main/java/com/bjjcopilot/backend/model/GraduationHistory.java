package com.bjjcopilot.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "graduation_history")
public class GraduationHistory {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    
    @NotNull
    @Column(name = "user_id")
    private UUID userId;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "from_belt_id")
    private Belt fromBelt;
    
    @NotNull
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "to_belt_id")
    private Belt toBelt;
    
    @NotNull
    @Column(name = "graduation_date")
    private LocalDate graduationDate;
    
    @NotNull
    @Column(name = "instructor_id")
    private UUID instructorId;
    
    @Size(max = 1000)
    private String notes;
    
    @Column(name = "created_at")
    private LocalDateTime createdAt;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", insertable = false, updatable = false)
    private User user;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "instructor_id", insertable = false, updatable = false)
    private User instructor;
    
    // Constructors
    public GraduationHistory() {}
    
    public GraduationHistory(UUID userId, Belt fromBelt, Belt toBelt, LocalDate graduationDate, UUID instructorId, String notes) {
        this.userId = userId;
        this.fromBelt = fromBelt;
        this.toBelt = toBelt;
        this.graduationDate = graduationDate;
        this.instructorId = instructorId;
        this.notes = notes;
        this.createdAt = LocalDateTime.now();
    }
    
    // Lifecycle methods
    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }
    
    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    
    public UUID getUserId() { return userId; }
    public void setUserId(UUID userId) { this.userId = userId; }
    
    public Belt getFromBelt() { return fromBelt; }
    public void setFromBelt(Belt fromBelt) { this.fromBelt = fromBelt; }
    
    public Belt getToBelt() { return toBelt; }
    public void setToBelt(Belt toBelt) { this.toBelt = toBelt; }
    
    public LocalDate getGraduationDate() { return graduationDate; }
    public void setGraduationDate(LocalDate graduationDate) { this.graduationDate = graduationDate; }
    
    public UUID getInstructorId() { return instructorId; }
    public void setInstructorId(UUID instructorId) { this.instructorId = instructorId; }
    
    public String getNotes() { return notes; }
    public void setNotes(String notes) { this.notes = notes; }
    
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    
    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }
    
    public User getInstructor() { return instructor; }
    public void setInstructor(User instructor) { this.instructor = instructor; }
}