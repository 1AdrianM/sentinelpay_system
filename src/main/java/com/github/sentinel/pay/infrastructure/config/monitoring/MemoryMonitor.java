package com.github.sentinel.pay.infrastructure.config.monitoring;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;


@Component
public class MemoryMonitor {
    private static final org.slf4j.Logger log = LoggerFactory.getLogger(MemoryMonitor.class);

    // Se ejecuta cada 5 segundos
    @Scheduled(fixedRate = 5000)
    public void logMemoryUsage() {
        MemoryMXBean memoryBean = ManagementFactory.getMemoryMXBean();
        long usedMemory = memoryBean.getHeapMemoryUsage().getUsed() / (1024 * 1024);
        long maxMemory = memoryBean.getHeapMemoryUsage().getMax() / (1024 * 1024);

        log.info("Uso de Memoria Heap: {}MB / {}MB", usedMemory, maxMemory);
    }
}