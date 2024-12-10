package com.example.backend.services;

import com.example.backend.models.Log;
import com.example.backend.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LogService {

    @Autowired
    private LogRepository logRepository;

    public void clearLogs() {
        logRepository.deleteAll(); // Deletes all records in the log table
    }

    public void saveLog(String message) {
        Log log = new Log();
        log.setLogMessage(message);
        logRepository.save(log);
    }

    public List<Log> getLogs() {
        return logRepository.findAll();
    }

    public List<String> getFormattedLogs() {
        return logRepository.findAll().stream()
                .map(log -> "[" + log.getCreatedAt() + "] " + log.getLogMessage())
                .collect(Collectors.toList());
    }

    // New method to save Hibernate query logs
    public void saveHibernateQuery(String hibernateQuery) {
        Log log = new Log();
        log.setLogMessage("Hibernate: " + hibernateQuery);
        logRepository.save(log);
    }
}
