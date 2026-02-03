package com.sitesentinel.backend.controller;

import com.sitesentinel.backend.model.Monitor;
import com.sitesentinel.backend.repository.MonitorRepository;
import com.sitesentinel.backend.service.PlayWrightService; // 👈 Updated Import
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/monitors")
@CrossOrigin(origins = "*")
public class MonitorController {

    private final MonitorRepository repository;
    private final PlayWrightService playwrightService; // 👈 Updated Type

    // Inject PlayWrightService (capital W)
    public MonitorController(MonitorRepository repository, PlayWrightService playwrightService) {
        this.repository = repository;
        this.playwrightService = playwrightService;
    }

    @GetMapping
    public List<Monitor> getAllMonitors() {
        return repository.findAll();
    }

    @PostMapping
    public Monitor addMonitor(@RequestBody Monitor monitor) {
        if (monitor.getIntervalMinutes() == 0) monitor.setIntervalMinutes(15);
        return repository.save(monitor);
    }

    @DeleteMapping("/{id}")
    public void deleteMonitor(@PathVariable Long id) {
        repository.deleteById(id);
    }

    // Manual Trigger Endpoint
    @PostMapping("/{id}/check")
    public ResponseEntity<Monitor> checkNow(@PathVariable Long id) {
        return repository.findById(id).map(monitor -> {
            // 1. Run the check immediately using the updated service name
            PlayWrightService.CheckResult result = playwrightService.performCheck(monitor.getUrl());

            // 2. Update the DB
            monitor.setLastChecked(LocalDateTime.now());
            monitor.setLastStatus(result.status());
            monitor.setResponseTimeMs(result.responseTime());
            monitor.setUp(result.status() >= 200 && result.status() < 300);

            if (result.screenshotFileName() != null) {
                monitor.setScreenshotFile(result.screenshotFileName());
            }

            Monitor updated = repository.save(monitor);
            return ResponseEntity.ok(updated);
        }).orElse(ResponseEntity.notFound().build());
    }
}