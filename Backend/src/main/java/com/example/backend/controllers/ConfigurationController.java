package com.example.backend.controllers;

import com.example.backend.models.Configuration;
import com.example.backend.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ConfigurationController {
    @Autowired
    private ConfigurationService configurationService;

    @PostMapping("/config")
    public String saveConfiguration(@RequestBody Configuration configuration) {
        configurationService.saveConfiguration(configuration);
        return "Configuration saved!";
    }
}
