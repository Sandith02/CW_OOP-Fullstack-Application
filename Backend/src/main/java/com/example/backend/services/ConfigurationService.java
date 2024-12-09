package com.example.backend.services;

import com.example.backend.models.Configuration;
import com.example.backend.repositories.ConfigurationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
    @Autowired
    private ConfigurationRepository configurationRepository;

    public void saveConfiguration(Configuration configuration) {
        configurationRepository.save(configuration);
    }

    public Configuration getConfiguration() {
        return configurationRepository.findAll().stream().findFirst().orElse(null);
    }
}
