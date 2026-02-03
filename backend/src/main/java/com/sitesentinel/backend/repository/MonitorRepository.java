package com.sitesentinel.backend.repository;

import com.sitesentinel.backend.model.Monitor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MonitorRepository extends JpaRepository<Monitor, Long> {
    // We can add custom queries here later, e.g., findByIsUpFalse()
}