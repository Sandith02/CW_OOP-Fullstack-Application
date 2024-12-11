package com.example.backend.controllers;

import com.example.backend.services.LogService;
import com.example.backend.simulation.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing simulation operations.
 * Provides endpoints to start and stop the simulation and retrieve simulation logs.
 */
@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @Autowired
    private LogService logService;

    /**
     * Starts the simulation with the specified parameters.
     *
     * @param totalTickets the total number of tickets to include in the simulation
     * @param ticketReleaseRate the rate at which tickets are released (tickets per second)
     * @param retrievalRate the rate at which customers retrieve tickets (customers per second)
     * @return a message indicating the simulation has started
     */
    @PostMapping("/start")
    public String startSimulation(@RequestParam int totalTickets,
                                  @RequestParam int ticketReleaseRate,
                                  @RequestParam int retrievalRate) {
        logService.clearLogs();
        simulationService.startSimulation(totalTickets, ticketReleaseRate, retrievalRate);
        return "Simulation started";
    }

    /**
     * Stops the running simulation.
     *
     * @return a message indicating the simulation has stopped
     */
    @PostMapping("/stop")
    public String stopSimulation() {
        simulationService.stopSimulation();
        return "Simulation stopped";
    }

    /**
     * Retrieves formatted logs of the simulation.
     *
     * @return a list of formatted log messages
     */
    @GetMapping("/logs")
    public List<String> getLogs() {
        return logService.getFormattedLogs();
    }
}