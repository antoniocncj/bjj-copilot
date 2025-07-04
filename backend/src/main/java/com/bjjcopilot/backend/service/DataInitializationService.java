package com.bjjcopilot.backend.service;

import com.bjjcopilot.backend.model.Belt;
import com.bjjcopilot.backend.model.BeltName;
import com.bjjcopilot.backend.repository.BeltRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializationService implements CommandLineRunner {
    
    private final BeltRepository beltRepository;
    
    @Autowired
    public DataInitializationService(BeltRepository beltRepository) {
        this.beltRepository = beltRepository;
    }
    
    @Override
    public void run(String... args) throws Exception {
        initializeBelts();
    }
    
    private void initializeBelts() {
        // Only initialize if no belts exist
        if (beltRepository.count() > 0) {
            return;
        }
        
        // White belt (degrees 0-4)
        beltRepository.save(new Belt(BeltName.WHITE, 0, "#FFFFFF", 1));
        beltRepository.save(new Belt(BeltName.WHITE, 1, "#FFFFFF", 2));
        beltRepository.save(new Belt(BeltName.WHITE, 2, "#FFFFFF", 3));
        beltRepository.save(new Belt(BeltName.WHITE, 3, "#FFFFFF", 4));
        beltRepository.save(new Belt(BeltName.WHITE, 4, "#FFFFFF", 5));
        
        // Blue belt (degrees 0-4)
        beltRepository.save(new Belt(BeltName.BLUE, 0, "#0000FF", 6));
        beltRepository.save(new Belt(BeltName.BLUE, 1, "#0000FF", 7));
        beltRepository.save(new Belt(BeltName.BLUE, 2, "#0000FF", 8));
        beltRepository.save(new Belt(BeltName.BLUE, 3, "#0000FF", 9));
        beltRepository.save(new Belt(BeltName.BLUE, 4, "#0000FF", 10));
        
        // Purple belt (degrees 0-4)
        beltRepository.save(new Belt(BeltName.PURPLE, 0, "#800080", 11));
        beltRepository.save(new Belt(BeltName.PURPLE, 1, "#800080", 12));
        beltRepository.save(new Belt(BeltName.PURPLE, 2, "#800080", 13));
        beltRepository.save(new Belt(BeltName.PURPLE, 3, "#800080", 14));
        beltRepository.save(new Belt(BeltName.PURPLE, 4, "#800080", 15));
        
        // Brown belt (degrees 0-4)
        beltRepository.save(new Belt(BeltName.BROWN, 0, "#8B4513", 16));
        beltRepository.save(new Belt(BeltName.BROWN, 1, "#8B4513", 17));
        beltRepository.save(new Belt(BeltName.BROWN, 2, "#8B4513", 18));
        beltRepository.save(new Belt(BeltName.BROWN, 3, "#8B4513", 19));
        beltRepository.save(new Belt(BeltName.BROWN, 4, "#8B4513", 20));
        
        // Black belt (degrees 0-10)
        beltRepository.save(new Belt(BeltName.BLACK, 0, "#000000", 21));
        beltRepository.save(new Belt(BeltName.BLACK, 1, "#000000", 22));
        beltRepository.save(new Belt(BeltName.BLACK, 2, "#000000", 23));
        beltRepository.save(new Belt(BeltName.BLACK, 3, "#000000", 24));
        beltRepository.save(new Belt(BeltName.BLACK, 4, "#000000", 25));
        beltRepository.save(new Belt(BeltName.BLACK, 5, "#000000", 26));
        beltRepository.save(new Belt(BeltName.BLACK, 6, "#000000", 27));
        beltRepository.save(new Belt(BeltName.BLACK, 7, "#000000", 28));
        beltRepository.save(new Belt(BeltName.BLACK, 8, "#000000", 29));
        beltRepository.save(new Belt(BeltName.BLACK, 9, "#000000", 30));
        beltRepository.save(new Belt(BeltName.BLACK, 10, "#000000", 31));
    }
}