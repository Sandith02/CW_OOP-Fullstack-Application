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
     * @param totalTickets the total number of tickets to be released
     * @param releaseRate the rate at which tickets are released by the vendor
     * @param retrievalRate the rate at which tickets are retrieved by the customer
     * @return a success message
     */
    @PostMapping("/start")
    public String startSimulation(
            @RequestParam int totalTickets,
            @RequestParam int releaseRate,
            @RequestParam int retrievalRate
    ) {
        simulationService.startSimulation(totalTickets, releaseRate, retrievalRate);
        logService.saveLog("Simulation started with configuration: " +
                "{totalTickets=" + totalTickets +
                ", releaseRate=" + releaseRate +
                ", retrievalRate=" + retrievalRate + "}");
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
