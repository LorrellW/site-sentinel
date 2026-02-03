package com.sitesentinel.backend.model;

import jakarta.persistence.*;
import lombok.Data;
import java.time.LocalDateTime;

@Entity
@Data // Lombok automatically generates Getters, Setters, toString, etc.
public class Monitor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // e.g., "Production Homepage"
    private String url;  // e.g., "https://google.com"

    private int intervalMinutes; // How often to check

    private LocalDateTime lastChecked;
    private int lastStatus; // e.g., 200, 404, 500
    private long responseTimeMs;
    private boolean isUp; // True if status is 200-299
    private String screenshotFile;
}