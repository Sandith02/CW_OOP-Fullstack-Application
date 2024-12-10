package com.example.backend.repositories;

import com.example.backend.models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TicketRepository extends JpaRepository<Ticket, Long> {
    @Query("SELECT t FROM Ticket t WHERE t.status = 'AVAILABLE'")
    List<Ticket> findAvailableTickets();

    List<Ticket> findByStatus(String status);
}
