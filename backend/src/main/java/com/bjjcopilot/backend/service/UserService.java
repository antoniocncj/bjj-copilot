package com.bjjcopilot.backend.service;

import com.bjjcopilot.backend.dto.*;
import com.bjjcopilot.backend.model.User;
import com.bjjcopilot.backend.model.UserProfile;
import com.bjjcopilot.backend.model.UserRole;
import com.bjjcopilot.backend.repository.UserRepository;
import com.bjjcopilot.backend.repository.UserProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Transactional
public class UserService {
    
    private final UserRepository userRepository;
    private final UserProfileRepository userProfileRepository;
    
    @Autowired
    public UserService(UserRepository userRepository, UserProfileRepository userProfileRepository) {
        this.userRepository = userRepository;
        this.userProfileRepository = userProfileRepository;
    }
    
    public UserResponse createUser(CreateUserRequest request) {
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("User with email " + request.getEmail() + " already exists");
        }
        
        User user = new User(request.getEmail(), request.getName(), request.getRole());
        user = userRepository.save(user);
        
        return convertToUserResponse(user);
    }
    
    public Optional<UserResponse> getUserById(UUID id) {
        return userRepository.findById(id)
                .map(this::convertToUserResponse);
    }
    
    public Optional<UserResponse> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(this::convertToUserResponse);
    }
    
    public List<UserResponse> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }
    
    public List<UserResponse> getUsersByRole(UserRole role) {
        return userRepository.findByRoleOrderByCreatedAtDesc(role)
                .stream()
                .map(this::convertToUserResponse)
                .collect(Collectors.toList());
    }
    
    public UserResponse updateUser(UUID id, CreateUserRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("User not found with id: " + id));
        
        // Check email uniqueness if email is being changed
        if (!user.getEmail().equals(request.getEmail()) && 
            userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("User with email " + request.getEmail() + " already exists");
        }
        
        user.setEmail(request.getEmail());
        user.setName(request.getName());
        user.setRole(request.getRole());
        
        user = userRepository.save(user);
        return convertToUserResponse(user);
    }
    
    public void deleteUser(UUID id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }
    
    private UserResponse convertToUserResponse(User user) {
        UserResponse response = new UserResponse(
                user.getId(),
                user.getEmail(),
                user.getName(),
                user.getRole(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
        
        // Load profile if exists
        userProfileRepository.findByUserId(user.getId())
                .ifPresent(profile -> response.setProfile(convertToUserProfileResponse(profile)));
        
        return response;
    }
    
    private UserProfileResponse convertToUserProfileResponse(UserProfile profile) {
        BeltResponse beltResponse = null;
        if (profile.getCurrentBelt() != null) {
            beltResponse = new BeltResponse(
                    profile.getCurrentBelt().getId(),
                    profile.getCurrentBelt().getName(),
                    profile.getCurrentBelt().getDegree(),
                    profile.getCurrentBelt().getColor(),
                    profile.getCurrentBelt().getOrder()
            );
        }
        
        return new UserProfileResponse(
                profile.getId(),
                profile.getDateOfBirth(),
                profile.getAcademy(),
                beltResponse,
                profile.getStartDate(),
                profile.getPreferences()
        );
    }
}