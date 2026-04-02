package com.github.sentinel.pay.infrastructure.startup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import lombok.RequiredArgsConstructor;
/* 
@Component
@RequiredArgsConstructor
public class DatabaseCleanserRunner implements CommandLineRunner {

    private static final Logger log = LoggerFactory.getLogger(DatabaseCleanserRunner.class);
    private final JdbcTemplate jdbcTemplate;


    @Override
    public void run(String... args) throws Exception {
             
        log.info("Iniciando limpieza masiva de tablas (Enfoque DDD - Sin FK físicas)...");
        try {
            // Usamos TRUNCATE por velocidad y limpieza de espacio
            jdbcTemplate.execute("TRUNCATE TABLE fraud_incidents");
            jdbcTemplate.execute("TRUNCATE TABLE fraud_decisions");
            jdbcTemplate.execute("TRUNCATE TABLE transactions");
            
            log.info("¡Limpieza completada exitosamente! Tablas listas para nuevos datos.");
        } catch (Exception e) {
            log.error("Error durante la limpieza de tablas: {}", e.getMessage());
        }
    }
}*/
