package com.bjjcopilot.backend;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@ActiveProfiles("test")
class BjjCopilotBackendApplicationTests {

    @Test
    void contextLoads() {
        // Basic test to ensure Spring context loads properly
    }

}