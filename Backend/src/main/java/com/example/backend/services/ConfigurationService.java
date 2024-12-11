package com.example.backend.services;

import com.example.backend.models.Configuration;
import com.example.backend.repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Service class for managing application configuration settings.
 * This service provides methods to save and retrieve configuration data.
 */
@Service
public class ConfigurationService {

    @Autowired
    private ConfigurationRepository configurationRepository;

    /**
     * Saves the given configuration to the database.
     *
     * @param configuration the {@link Configuration} object to save
     */
    public void saveConfiguration(Configuration configuration) {
        configurationRepository.save(configuration);
    }

    /**
     * Retrieves the first available configuration from the database.
     * If no configurations exist, it returns {@code null}.
     *
     * @return the first {@link Configuration} object or {@code null} if none exist
     */
    public Configuration getConfiguration() {
        return configurationRepository.findAll().stream().findFirst().orElse(null);
    }
}
