package com.example.backend.services;

import com.example.backend.models.Log;
import com.example.backend.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class responsible for managing log operations, including clearing logs,
 * saving log messages, and retrieving logs in various formats.
 */
@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    /**
     * Clears all logs from the database.
     * This method deletes all records in the log table.
     */
    public void clearLogs() {
        logRepository.deleteAll(); // Deletes all records in the log table
    }

    /**
     * Saves a log message to the database.
     *
     * @param message the log message to save
     */
    public void saveLog(String message) {
        Log log = new Log();
        log.setLogMessage(message);
        logRepository.save(log);
    }

    /**
     * Retrieves all logs from the database.
     *
     * @return a list of {@link Log} objects
     */
    public List<Log> getLogs() {
        return logRepository.findAll();
    }

    /**
     * Retrieves all logs from the database and formats them with timestamps.
     *
     * @return a list of formatted log messages
     */
    public List<String> getFormattedLogs() {
        return logRepository.findAll().stream()
                .map(log -> "[" + log.getCreatedAt() + "] " + log.getLogMessage())
                .collect(Collectors.toList());
    }

    /**
     * Saves a Hibernate query log message to the database.
     *
     * @param hibernateQuery the Hibernate query string to log
     */
    public void saveHibernateQuery(String hibernateQuery) {
        Log log = new Log();
        log.setLogMessage("Hibernate: " + hibernateQuery);
        logRepository.save(log);
    }
}
