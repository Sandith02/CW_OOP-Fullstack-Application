package com.example.backend.simulation;

import com.example.backend.services.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class SimulationService {

    private ExecutorService executorService;

    @Autowired
    private TicketService ticketService;

    // Starts the simulation by running vendor and customer threads
    public void startSimulation() {
        // Initialize the thread pool with 2 threads: 1 Vendor + 1 Customer
        executorService = Executors.newFixedThreadPool(2);

        // Submit Vendor thread
        executorService.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    ticketService.addTickets(5); // Add 5 tickets per iteration
                    System.out.println("Vendor added 5 tickets.");
                    Thread.sleep(1000); // Simulate ticket addition rate (1 second)
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Vendor thread interrupted.");
            }
        });

        // Submit Customer thread
        executorService.submit(() -> {
            try {
                while (!Thread.currentThread().isInterrupted()) {
                    ticketService.removeTicket(); // Customer purchases 1 ticket per iteration
                    System.out.println("Customer purchased a ticket.");
                    Thread.sleep(1000); // Simulate customer retrieval rate (1 second)
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Customer thread interrupted.");
            }
        });
    }

    // Stops the simulation by shutting down all threads
    public void stopSimulation() {
        if (executorService != null) {
            executorService.shutdownNow();
            System.out.println("Simulation stopped and threads shut down.");
        }
    }
}
