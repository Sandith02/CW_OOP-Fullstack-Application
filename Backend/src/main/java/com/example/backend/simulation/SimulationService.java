package com.example.backend.simulation;

import com.example.backend.models.Ticket;
import com.example.backend.repositories.TicketRepository;
import com.example.backend.services.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimulationService {

    private boolean simulationRunning = false; // Flag to indicate if the simulation is running
    private int totalTickets;
    private int ticketReleaseRate;
    private int retrievalRate;

    @Autowired
    private LogService logService;

    @Autowired
    private TicketRepository ticketRepository;

    public void startSimulation(int totalTickets, int ticketReleaseRate, int retrievalRate) {
        if (simulationRunning) {
            logService.saveLog("Simulation is already running!");
            return;
        }

        this.simulationRunning = true;
        this.totalTickets = totalTickets;
        this.ticketReleaseRate = ticketReleaseRate;
        this.retrievalRate = retrievalRate;

        logService.saveLog("Simulation started!");

        // Create tickets
        for (int i = 0; i < totalTickets; i++) {
            Ticket ticket = new Ticket();
            ticket.setStatus("AVAILABLE");
            ticketRepository.save(ticket);
        }

        // Start simulation process
        new Thread(this::simulateTickets).start();
    }

    public void stopSimulation() {
        if (!simulationRunning) {
            logService.saveLog("Simulation is not running!");
            return;
        }

        this.simulationRunning = false;
        logService.saveLog("Simulation stopped!");
    }

    private void simulateTickets() {
        try {
            int ticketsSold = 0;

            while (simulationRunning && ticketsSold < totalTickets) {
                Thread.sleep(1000 / ticketReleaseRate); // Release rate delay

                Ticket ticket = ticketRepository.findAvailableTickets().stream().findFirst().orElse(null);
                if (ticket != null) {
                    ticket.setStatus("SOLD");
                    ticketRepository.save(ticket);
                    ticketsSold++;
                    logService.saveLog("Releasing ticket #" + ticketsSold);
                }

                Thread.sleep(1000 / retrievalRate); // Retrieval rate delay
                logService.saveLog("Customer retrieving a ticket.");

                // Stop simulation when all tickets are sold
                if (ticketsSold >= totalTickets) {
                    logService.saveLog("All tickets sold. Stopping simulation...");
                    stopSimulation();
                    break;
                }
            }
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logService.saveLog("Simulation interrupted: " + e.getMessage());
        }
    }
}
