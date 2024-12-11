package com.example.backend.controllers;

import com.example.backend.models.Configuration;
import com.example.backend.services.ConfigurationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * REST controller for managing system configurations.
 * Provides an endpoint to save configuration settings.
 */
@RestController
@RequestMapping("/api")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    /**
     * Saves the provided configuration to the system.
     *
     * @param configuration the {@link Configuration} object containing configuration settings
     * @return a confirmation message indicating the configuration has been saved
     */
    @PostMapping("/config")
    public String saveConfiguration(@RequestBody Configuration configuration) {
        configurationService.saveConfiguration(configuration);
        return "Configuration saved!";
    }
}