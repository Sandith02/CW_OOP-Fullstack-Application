package com.example.backend.repositories;

import com.example.backend.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository interface for managing {@link Ticket} entities.
 * Provides methods for retrieving and querying ticket data from the database.
 */
@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {

    /**
     * Retrieves a list of tickets with the status "AVAILABLE".
     *
     * @return a list of available {@link Ticket} objects
     */
    @Query("SELECT t FROM Ticket t WHERE t.status = 'AVAILABLE'")
    List<Ticket> findAvailableTickets();

    /**
     * Retrieves a list of tickets by their status.
     *
     * @param status the status of the tickets to find
     * @return a list of {@link Ticket} objects with the specified status
     */
    List<Ticket> findByStatus(String status);
}
