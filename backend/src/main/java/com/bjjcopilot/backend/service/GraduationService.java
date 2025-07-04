package com.bjjcopilot.backend.service;

import com.bjjcopilot.backend.dto.*;
import com.bjjcopilot.backend.model.*;
import com.bjjcopilot.backend.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class GraduationService {
    
    private final GraduationHistoryRepository graduationHistoryRepository;
    private final UserRepository userRepository;
    private final BeltRepository beltRepository;
    private final UserProfileRepository userProfileRepository;
    
    @Autowired
    public GraduationService(GraduationHistoryRepository graduationHistoryRepository,
                           UserRepository userRepository,
                           BeltRepository beltRepository,
                           UserProfileRepository userProfileRepository) {
        this.graduationHistoryRepository = graduationHistoryRepository;
        this.userRepository = userRepository;
        this.beltRepository = beltRepository;
        this.userProfileRepository = userProfileRepository;
    }
    
    public GraduationHistoryResponse createGraduation(CreateGraduationRequest request, UUID instructorId) {
        // Validate user exists
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + request.getUserId()));
        
        // Validate instructor exists
        User instructor = userRepository.findById(instructorId)
                .orElseThrow(() -> new IllegalArgumentException("Instructor not found with id: " + instructorId));
        
        if (instructor.getRole() != UserRole.INSTRUCTOR && instructor.getRole() != UserRole.ADMIN) {
            throw new IllegalArgumentException("Only instructors and admins can create graduations");
        }
        
        // Validate belts exist
        Belt fromBelt = null;
        if (request.getFromBeltId() != null) {
            fromBelt = beltRepository.findById(request.getFromBeltId())
                    .orElseThrow(() -> new IllegalArgumentException("From belt not found with id: " + request.getFromBeltId()));
        }
        
        Belt toBelt = beltRepository.findById(request.getToBeltId())
                .orElseThrow(() -> new IllegalArgumentException("To belt not found with id: " + request.getToBeltId()));
        
        // Create graduation history
        GraduationHistory graduation = new GraduationHistory(
                request.getUserId(),
                fromBelt,
                toBelt,
                request.getGraduationDate(),
                instructorId,
                request.getNotes()
        );
        
        graduation = graduationHistoryRepository.save(graduation);
        
        // Update user's current belt in profile
        updateUserCurrentBelt(request.getUserId(), toBelt);
        
        return convertToGraduationHistoryResponse(graduation, user.getName(), instructor.getName());
    }
    
    public List<GraduationHistoryResponse> getGraduationHistoryByUser(UUID userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + userId));
        
        return graduationHistoryRepository.findByUserIdOrderByGraduationDateDesc(userId)
                .stream()
                .map(graduation -> {
                    String instructorName = userRepository.findById(graduation.getInstructorId())
                            .map(User::getName)
                            .orElse("Unknown");
                    return convertToGraduationHistoryResponse(graduation, user.getName(), instructorName);
                })
                .collect(Collectors.toList());
    }
    
    public List<GraduationHistoryResponse> getGraduationsByInstructor(UUID instructorId) {
        return graduationHistoryRepository.findByInstructorIdOrderByGraduationDateDesc(instructorId)
                .stream()
                .map(graduation -> {
                    String userName = userRepository.findById(graduation.getUserId())
                            .map(User::getName)
                            .orElse("Unknown");
                    String instructorName = userRepository.findById(graduation.getInstructorId())
                            .map(User::getName)
                            .orElse("Unknown");
                    return convertToGraduationHistoryResponse(graduation, userName, instructorName);
                })
                .collect(Collectors.toList());
    }
    
    public List<GraduationHistoryResponse> getAllGraduations() {
        return graduationHistoryRepository.findAll()
                .stream()
                .map(graduation -> {
                    String userName = userRepository.findById(graduation.getUserId())
                            .map(User::getName)
                            .orElse("Unknown");
                    String instructorName = userRepository.findById(graduation.getInstructorId())
                            .map(User::getName)
                            .orElse("Unknown");
                    return convertToGraduationHistoryResponse(graduation, userName, instructorName);
                })
                .collect(Collectors.toList());
    }
    
    private void updateUserCurrentBelt(UUID userId, Belt newBelt) {
        Optional<UserProfile> profileOpt = userProfileRepository.findByUserId(userId);
        
        if (profileOpt.isPresent()) {
            UserProfile profile = profileOpt.get();
            profile.setCurrentBelt(newBelt);
            userProfileRepository.save(profile);
        } else {
            // Create a new profile if it doesn't exist
            UserProfile profile = new UserProfile();
            profile.setUserId(userId);
            profile.setCurrentBelt(newBelt);
            userProfileRepository.save(profile);
        }
    }
    
    private GraduationHistoryResponse convertToGraduationHistoryResponse(GraduationHistory graduation, 
                                                                        String userName, String instructorName) {
        BeltResponse fromBeltResponse = null;
        if (graduation.getFromBelt() != null) {
            fromBeltResponse = new BeltResponse(
                    graduation.getFromBelt().getId(),
                    graduation.getFromBelt().getName(),
                    graduation.getFromBelt().getDegree(),
                    graduation.getFromBelt().getColor(),
                    graduation.getFromBelt().getOrder()
            );
        }
        
        BeltResponse toBeltResponse = new BeltResponse(
                graduation.getToBelt().getId(),
                graduation.getToBelt().getName(),
                graduation.getToBelt().getDegree(),
                graduation.getToBelt().getColor(),
                graduation.getToBelt().getOrder()
        );
        
        return new GraduationHistoryResponse(
                graduation.getId(),
                graduation.getUserId(),
                userName,
                fromBeltResponse,
                toBeltResponse,
                graduation.getGraduationDate(),
                graduation.getInstructorId(),
                instructorName,
                graduation.getNotes(),
                graduation.getCreatedAt()
        );
    }
}