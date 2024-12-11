package com.example.backend.repositories;

import com.example.backend.models.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing {@link Configuration} entities.
 * Provides CRUD operations for configurations in the database.
 */
public interface ConfigurationRepository extends JpaRepository<Configuration, Long> {
}