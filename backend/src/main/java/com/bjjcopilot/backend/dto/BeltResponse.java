package com.bjjcopilot.backend.dto;

import com.bjjcopilot.backend.model.BeltName;
import java.util.UUID;

public class BeltResponse {
    
    private UUID id;
    private BeltName name;
    private Integer degree;
    private String color;
    private Integer order;
    private String displayName;
    
    // Constructors
    public BeltResponse() {}
    
    public BeltResponse(UUID id, BeltName name, Integer degree, String color, Integer order) {
        this.id = id;
        this.name = name;
        this.degree = degree;
        this.color = color;
        this.order = order;
        this.displayName = generateDisplayName();
    }
    
    private String generateDisplayName() {
        if (degree > 0) {
            return name.toString() + " " + degree + "°";
        }
        return name.toString();
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
    
    public String getDisplayName() { return displayName; }
    public void setDisplayName(String displayName) { this.displayName = displayName; }
}