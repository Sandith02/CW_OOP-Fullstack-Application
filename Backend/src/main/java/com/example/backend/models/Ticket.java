package com.example.backend.models;

import jakarta.persistence.*;

/**
 * Entity representing a ticket in the system.
 * A ticket can have a status of either "AVAILABLE" or "SOLD".
 */
@Entity
public class Ticket {

    /**
     * Unique identifier for the ticket.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Status of the ticket. Possible values: "AVAILABLE" or "SOLD".
     */
    private String status; // AVAILABLE or SOLD

    /**
     * Gets the unique identifier of the ticket.
     *
     * @return the ticket ID
     */
    public Long getId() { return id; }

    /**
     * Sets the unique identifier of the ticket.
     *
     * @param id the ticket ID to set
     */
    public void setId(Long id) { this.id = id; }

    /**
     * Gets the status of the ticket.
     *
     * @return the status of the ticket
     */
    public String getStatus() { return status; }

    /**
     * Sets the status of the ticket.
     *
     * @param status the status to set, either "AVAILABLE" or "SOLD"
     */
    public void setStatus(String status) { this.status = status; }
}
