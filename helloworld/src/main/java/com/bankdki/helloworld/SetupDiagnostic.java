package com.bankdki.helloworld;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SetupDiagnostic implements CommandLineRunner {

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) {
        System.out.println("=================================================");
        System.out.println("PRE-CLASS ENVIRONMENT DIAGNOSTIC CHECK");
        System.out.println("=================================================");
        
        // 1. Verify Java Version
        String javaVersion = System.getProperty("java.version");
        System.out.println(" Java Version Detected : " + javaVersion);

        // 2. Verify Database Connection
        try {
            Integer result = jdbcTemplate.queryForObject("SELECT 1", new java.util.HashMap<>(), Integer.class);
            if (result != null && result == 1) {
                System.out.println(" H2 Database Status     : CONNECTED (OK)");
            }
        } catch (Exception e) {
            System.err.println(" H2 Database Status     : FAILED (" + e.getMessage() + ")");
        }

        System.out.println("=================================================");
        System.out.println(" READY FOR CLASS! Test URL: http://localhost:8080/calc?left=100&right=100");
        System.out.println("=================================================");
    }
}