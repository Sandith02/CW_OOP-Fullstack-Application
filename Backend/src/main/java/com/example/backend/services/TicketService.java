package com.example.backend.services;

import com.example.backend.models.Ticket;
import com.example.backend.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TicketService {
    @Autowired
    private TicketRepository ticketRepository;

    public void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            Ticket ticket = new Ticket();
            ticket.setStatus("AVAILABLE");
            ticketRepository.save(ticket);
        }
    }

    public Ticket removeTicket() {
        List<Ticket> availableTickets = ticketRepository.findAvailableTickets();
        if (!availableTickets.isEmpty()) {
            Ticket ticket = availableTickets.get(0);
            ticket.setStatus("SOLD");
            ticketRepository.save(ticket);
            return ticket;
        }
        return null;
    }

    public long getAvailableTicketCount() {
        return ticketRepository.findAvailableTickets().size();
    }
}
