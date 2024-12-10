package com.example.backend.controllers;

import com.example.backend.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LogController {

    @Autowired
    private LogService logService;

    @GetMapping("/api/logs")
    public List<String> getLogs() {
        return logService.getFormattedLogs();
    }
}
