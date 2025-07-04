package com.bjjcopilot.backend.service;

import com.bjjcopilot.backend.dto.BeltResponse;
import com.bjjcopilot.backend.model.Belt;
import com.bjjcopilot.backend.model.BeltName;
import com.bjjcopilot.backend.repository.BeltRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class BeltService {
    
    private final BeltRepository beltRepository;
    
    @Autowired
    public BeltService(BeltRepository beltRepository) {
        this.beltRepository = beltRepository;
    }
    
    public List<BeltResponse> getAllBelts() {
        return beltRepository.findAllOrderByOrder()
                .stream()
                .map(this::convertToBeltResponse)
                .collect(Collectors.toList());
    }
    
    public Optional<BeltResponse> getBeltById(UUID id) {
        return beltRepository.findById(id)
                .map(this::convertToBeltResponse);
    }
    
    public List<BeltResponse> getBeltsByName(BeltName name) {
        return beltRepository.findByNameOrderByDegreeAsc(name)
                .stream()
                .map(this::convertToBeltResponse)
                .collect(Collectors.toList());
    }
    
    public Optional<BeltResponse> getBeltByNameAndDegree(BeltName name, Integer degree) {
        return beltRepository.findByNameAndDegree(name, degree)
                .map(this::convertToBeltResponse);
    }
    
    private BeltResponse convertToBeltResponse(Belt belt) {
        return new BeltResponse(
                belt.getId(),
                belt.getName(),
                belt.getDegree(),
                belt.getColor(),
                belt.getOrder()
        );
    }
}