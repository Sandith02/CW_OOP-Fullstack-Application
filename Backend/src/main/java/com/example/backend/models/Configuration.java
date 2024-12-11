package com.example.backend.models;

import jakarta.persistence.*;

/**
 * Entity representing the configuration settings for the system.
 * This includes parameters such as total tickets, ticket release rate,
 * customer retrieval rate, and maximum ticket capacity.
 */
@Entity
public class Configuration {

    /**
     * Unique identifier for the configuration.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Total number of tickets available in the system.
     */
    private int totalTickets;

    /**
     * Rate at which tickets are released (tickets per second).
     */
    private int ticketReleaseRate;

    /**
     * Rate at which customers retrieve tickets (customers per second).
     */
    private int customerRetrievalRate;

    /**
     * Maximum capacity of tickets that the system can handle.
     */
    private int maxTicketCapacity;

    /**
     * Gets the unique identifier of the configuration.
     *
     * @return the configuration ID
     */
    public Long getId() { return id; }

    /**
     * Sets the unique identifier of the configuration.
     *
     * @param id the configuration ID to set
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Gets the total number of tickets available.
     *
     * @return the total number of tickets
     */
    public int getTotalTickets() { return totalTickets; }

    /**
     * Sets the total number of tickets available.
     *
     * @param totalTickets the total number of tickets to set
     */
    public void setTotalTickets(int totalTickets) { this.totalTickets = totalTickets; }

    /**
     * Gets the rate at which tickets are released.
     *
     * @return the ticket release rate
     */
    public int getTicketReleaseRate() { return ticketReleaseRate; }

    /**
     * Sets the rate at which tickets are released.
     *
     * @param ticketReleaseRate the ticket release rate to set
     */
    public void setTicketReleaseRate(int ticketReleaseRate) { this.ticketReleaseRate = ticketReleaseRate; }

    /**
     * Gets the rate at which customers retrieve tickets.
     *
     * @return the customer retrieval rate
     */
    public int getCustomerRetrievalRate() { return customerRetrievalRate; }

    /**
     * Sets the rate at which customers retrieve tickets.
     *
     * @param customerRetrievalRate the customer retrieval rate to set
     */
    public void setCustomerRetrievalRate(int customerRetrievalRate) { this.customerRetrievalRate = customerRetrievalRate; }

    /**
     * Gets the maximum capacity of tickets the system can handle.
     *
     * @return the maximum ticket capacity
     */
    public int getMaxTicketCapacity() { return maxTicketCapacity; }

    /**
     * Sets the maximum capacity of tickets the system can handle.
     *
     * @param maxTicketCapacity the maximum ticket capacity to set
     */
    public void setMaxTicketCapacity(int maxTicketCapacity) { this.maxTicketCapacity = maxTicketCapacity; }
}
