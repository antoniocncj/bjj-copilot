package com.bjjcopilot.backend.controller;

import com.bjjcopilot.backend.dto.BeltResponse;
import com.bjjcopilot.backend.model.BeltName;
import com.bjjcopilot.backend.service.BeltService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/belts")
public class BeltController {
    
    private final BeltService beltService;
    
    @Autowired
    public BeltController(BeltService beltService) {
        this.beltService = beltService;
    }
    
    @GetMapping
    public ResponseEntity<List<BeltResponse>> getAllBelts(@RequestParam(required = false) BeltName name) {
        List<BeltResponse> belts;
        if (name != null) {
            belts = beltService.getBeltsByName(name);
        } else {
            belts = beltService.getAllBelts();
        }
        return ResponseEntity.ok(belts);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<BeltResponse> getBeltById(@PathVariable UUID id) {
        return beltService.getBeltById(id)
                .map(belt -> ResponseEntity.ok(belt))
                .orElse(ResponseEntity.notFound().build());
    }
    
    @GetMapping("/search")
    public ResponseEntity<BeltResponse> getBeltByNameAndDegree(@RequestParam BeltName name, 
                                                              @RequestParam Integer degree) {
        return beltService.getBeltByNameAndDegree(name, degree)
                .map(belt -> ResponseEntity.ok(belt))
                .orElse(ResponseEntity.notFound().build());
    }
}