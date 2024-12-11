package com.example.backend.repositories;

import com.example.backend.models.Log;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Repository interface for managing {@link Log} entities.
 * Provides CRUD operations for logs in the database.
 */
@Repository
public interface LogRepository extends JpaRepository<Log, Long> {
}