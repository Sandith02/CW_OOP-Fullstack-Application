package com.example.backend.controllers;

import com.example.backend.services.LogService;
import com.example.backend.simulation.SimulationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulation")
public class SimulationController {

    @Autowired
    private SimulationService simulationService;

    @Autowired
    private LogService logService;

    @PostMapping("/start")
    public String startSimulation(@RequestParam int totalTickets,
                                  @RequestParam int ticketReleaseRate,
                                  @RequestParam int retrievalRate) {
        logService.clearLogs();
        simulationService.startSimulation(totalTickets, ticketReleaseRate, retrievalRate);
        return "Simulation started";
    }

    @PostMapping("/stop")
    public String stopSimulation() {
        simulationService.stopSimulation();
        return "Simulation stopped";
    }

    @GetMapping("/logs")
    public List<String> getLogs() {
        return logService.getFormattedLogs();
    }
}
