package com.example.backend.services;

import com.example.backend.models.Log;
import com.example.backend.repositories.LogRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogService {
    @Autowired
    private LogRepository logRepository;

    public void saveLog(String message) {
        Log log = new Log();
        log.setLogMessage(message);
        logRepository.save(log);
    }

    public List<Log> getLogs() {
        return logRepository.findAll();
    }
}
