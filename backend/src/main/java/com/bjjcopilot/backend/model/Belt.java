package com.bjjcopilot.backend.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import java.util.UUID;

@Entity
@Table(name = "belts")
public class Belt {
    
    @Id
    @GeneratedValue
    @Column(columnDefinition = "uuid")
    private UUID id;
    
    @NotNull
    @Enumerated(EnumType.STRING)
    private BeltName name;
    
    @NotNull
    @Min(0)
    @Max(10)
    private Integer degree;
    
    @NotNull
    @Size(max = 50)
    private String color;
    
    @NotNull
    @Column(name = "sort_order")
    private Integer order;
    
    // Constructors
    public Belt() {}
    
    public Belt(BeltName name, Integer degree, String color, Integer order) {
        this.name = name;
        this.degree = degree;
        this.color = color;
        this.order = order;
    }
    
    // Getters and Setters
    public UUID getId() { return id; }
    public void setId(UUID id) { this.id = id; }
    
    public BeltName getName() { return name; }
    public void setName(BeltName name) { this.name = name; }
    
    public Integer getDegree() { return degree; }
    public void setDegree(Integer degree) { this.degree = degree; }
    
    public String getColor() { return color; }
    public void setColor(String color) { this.color = color; }
    
    public Integer getOrder() { return order; }
    public void setOrder(Integer order) { this.order = order; }
    
    // Helper methods
    public String getDisplayName() {
        if (degree > 0) {
            return name.toString() + " " + degree + "°";
        }
        return name.toString();
    }
}