package com.example.backend.controllers;

import com.example.backend.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * REST controller for managing logs.
 * Provides an endpoint to retrieve formatted log messages.
 */
@RestController
public class LogController {

    @Autowired
    private LogService logService;

    /**
     * Retrieves formatted logs from the system.
     *
     * @return a list of formatted log messages
     */
    @GetMapping("/api/logs")
    public List<String> getLogs() {
        return logService.getFormattedLogs();
    }
}
