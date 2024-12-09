package com.example.backend.controllers;

import com.example.backend.services.LogService;
import com.example.backend.services.TicketService;
import com.example.backend.simulation.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @Autowired
    private TicketService ticketService;

    @Autowired
    private LogService logService;

    /**
     * Start the simulation with vendors and customers.
     *
     * @return a success message
     */
    @PostMapping("/start")
    public String startSimulation() {
        simulationService.startSimulation();
        logService.saveLog("Simulation started!");
        return "Simulation started successfully!";
    }

    /**
     * Stop the simulation by shutting down vendor and customer threads.
     *
     * @return a success message
     */
    @PostMapping("/stop")
    public String stopSimulation() {
        simulationService.stopSimulation();
        logService.saveLog("Simulation stopped!");
        return "Simulation stopped successfully!";
    }

    /**
     * Get the real-time status of available tickets and logs.
     *
     * @return a map containing ticket count and logs
     */
    @GetMapping("/status")
    public Map<String, Object> getStatus() {
        Map<String, Object> status = new HashMap<>();
        status.put("availableTickets", ticketService.getAvailableTicketCount());
        status.put("logs", logService.getLogs());
        return status;
    }
}
