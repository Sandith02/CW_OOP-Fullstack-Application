package com.example.backend.simulation;

import com.example.backend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SimulationService {

    private ExecutorService executorService;
    private boolean isRunning = false;
    private int totalTicketsSold = 0; // Counter to track sold tickets

    @Autowired
    private TicketService ticketService;

    // Starts the simulation by running vendor and customer threads
    public synchronized void startSimulation(int totalTickets, int releaseRate, int retrievalRate) {
        if (isRunning) {
            System.out.println("Simulation is already running.");
            return;
        }

        isRunning = true;
        totalTicketsSold = 0;
        executorService = Executors.newFixedThreadPool(2); // 1 Vendor + 1 Customer

        // Vendor thread: Adds tickets periodically
        executorService.submit(() -> {
            try {
                while (isRunning && totalTicketsSold < totalTickets) {
                    synchronized (this) {
                        if (ticketService.getAvailableTicketCount() < totalTickets) {
                            ticketService.addTickets(releaseRate); // Add tickets
                            System.out.println("Vendor added " + releaseRate + " tickets.");
                        }
                    }
                    Thread.sleep(1000); // Simulate ticket release rate
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor thread interrupted.");
            }
        });

        // Customer thread: Buys tickets periodically
        executorService.submit(() -> {
            try {
                while (isRunning && totalTicketsSold < totalTickets) {
                    synchronized (this) {
                        if (ticketService.getAvailableTicketCount() > 0) {
                            ticketService.removeTicket(); // Sell a ticket
                            totalTicketsSold++;
                            System.out.println("Customer purchased a ticket. Total sold: " + totalTicketsSold);
                        }
                    }
                    Thread.sleep(1000); // Simulate customer retrieval rate
                }
                stopSimulation(); // Stop simulation when all tickets are sold
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer thread interrupted.");
            }
        });
    }

    // Stops the simulation by shutting down all threads
    public synchronized void stopSimulation() {
        if (!isRunning) return;
        isRunning = false;
        if (executorService != null) {
            executorService.shutdownNow();
            System.out.println("Simulation stopped and threads shut down.");
        }
    }
}
