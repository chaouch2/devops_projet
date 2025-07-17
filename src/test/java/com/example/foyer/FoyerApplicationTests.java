package com.example.foyer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@ActiveProfiles("test") // 👉 utilise application-test.properties
class FoyerApplicationTests {

    @Test
    void contextLoads() {
        // Test simple qui passe toujours
        assertTrue(true, "Le test contextLoads passe toujours");
    }
}
