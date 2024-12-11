package com.example.backend.models;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 * Entity representing a log entry in the system.
 * Logs capture messages and their corresponding timestamps.
 */
@Entity
public class Log {

    /**
     * Unique identifier for the log entry.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Message associated with the log entry.
     */
    private String logMessage;

    /**
     * Timestamp indicating when the log entry was created.
     */
    private LocalDateTime createdAt = LocalDateTime.now();

    /**
     * Gets the unique identifier of the log entry.
     *
     * @return the log entry ID
     */
    public Long getId() { return id; }

    /**
     * Sets the unique identifier of the log entry.
     *
     * @param id the log entry ID to set
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Gets the message associated with the log entry.
     *
     * @return the log message
     */
    public String getLogMessage() { return logMessage; }

    /**
     * Sets the message for the log entry.
     *
     * @param logMessage the log message to set
     */
    public void setLogMessage(String logMessage) { this.logMessage = logMessage; }

    /**
     * Gets the timestamp indicating when the log entry was created.
     *
     * @return the creation timestamp
     */
    public LocalDateTime getCreatedAt() { return createdAt; }

    /**
     * Sets the timestamp for when the log entry was created.
     *
     * @param createdAt the creation timestamp to set
     */
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}
