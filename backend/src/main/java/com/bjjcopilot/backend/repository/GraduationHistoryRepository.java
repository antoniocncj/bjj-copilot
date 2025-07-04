package com.bjjcopilot.backend.repository;

import com.bjjcopilot.backend.model.GraduationHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Repository
public interface GraduationHistoryRepository extends JpaRepository<GraduationHistory, UUID> {
    
    List<GraduationHistory> findByUserIdOrderByGraduationDateDesc(UUID userId);
    
    List<GraduationHistory> findByInstructorIdOrderByGraduationDateDesc(UUID instructorId);
    
    @Query("SELECT gh FROM GraduationHistory gh WHERE gh.graduationDate BETWEEN :startDate AND :endDate ORDER BY gh.graduationDate DESC")
    List<GraduationHistory> findByGraduationDateBetween(LocalDate startDate, LocalDate endDate);
    
    @Query("SELECT gh FROM GraduationHistory gh WHERE gh.userId = :userId ORDER BY gh.graduationDate DESC")
    List<GraduationHistory> findLatestGraduationsByUserId(UUID userId);
}