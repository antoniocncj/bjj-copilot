package com.bjjcopilot.backend.repository;

import com.bjjcopilot.backend.model.Belt;
import com.bjjcopilot.backend.model.BeltName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface BeltRepository extends JpaRepository<Belt, UUID> {
    
    Optional<Belt> findByNameAndDegree(BeltName name, Integer degree);
    
    List<Belt> findByNameOrderByDegreeAsc(BeltName name);
    
    @Query("SELECT b FROM Belt b ORDER BY b.order ASC")
    List<Belt> findAllOrderByOrder();
}