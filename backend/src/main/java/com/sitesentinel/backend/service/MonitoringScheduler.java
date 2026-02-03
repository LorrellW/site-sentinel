package com.sitesentinel.backend.service;

import com.sitesentinel.backend.model.Monitor;
import com.sitesentinel.backend.repository.MonitorRepository;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
public class MonitoringScheduler {

    private final MonitorRepository repository;
    private final PlayWrightService playwrightService;

    public MonitoringScheduler(MonitorRepository repository, PlayWrightService playwrightService) {
        this.repository = repository;
        this.playwrightService = playwrightService;
    }

    // Run every 60 seconds
    //@Scheduled(fixedRate = 60000)
    public void runChecks() {
        List<Monitor> monitors = repository.findAll();

        if (monitors.isEmpty()) return;

        System.out.println("Running checks for " + monitors.size() + " sites...");

        for (Monitor monitor : monitors) {
            try {
                PlayWrightService.CheckResult result = playwrightService.performCheck(monitor.getUrl());

                monitor.setLastChecked(LocalDateTime.now());
                monitor.setLastStatus(result.status());
                monitor.setResponseTimeMs(result.responseTime());
                monitor.setUp(result.status() >= 200 && result.status() < 300);

                if (result.screenshotFileName() != null) {
                    monitor.setScreenshotFile(result.screenshotFileName());
                }

                repository.save(monitor);
            } catch (Exception e) {
                System.err.println("Error checking " + monitor.getUrl() + ": " + e.getMessage());
            }
        }
    }
}