package com.example.backend.services;

import com.example.backend.models.Ticket;
import com.example.backend.repositories.TicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class responsible for managing ticket operations such as adding,
 * removing, and counting available tickets.
 */
@Service
public class TicketService {

    @Autowired
    private TicketRepository ticketRepository;

    @Autowired
    private LogService logService;

    /**
     * Adds a specified number of tickets to the system with an "AVAILABLE" status.
     *
     * @param count the number of tickets to add
     */
    public void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            Ticket ticket = new Ticket();
            ticket.setStatus("AVAILABLE");
            ticketRepository.save(ticket);
            logService.saveHibernateQuery("insert into ticket (status) values ('AVAILABLE')");
        }
    }

    /**
     * Removes one "AVAILABLE" ticket by marking it as "SOLD".
     *
     * @return the ticket that was updated to "SOLD", or {@code null} if no tickets are available
     */
    public Ticket removeTicket() {
        List<Ticket> availableTickets = ticketRepository.findByStatus("AVAILABLE");
        logService.saveHibernateQuery("select t.id, t.status from ticket t where t.status = 'AVAILABLE'");
        if (!availableTickets.isEmpty()) {
            Ticket ticket = availableTickets.get(0);
            ticket.setStatus("SOLD");
            ticketRepository.save(ticket);
            logService.saveHibernateQuery("update ticket set status = 'SOLD' where id = " + ticket.getId());
            return ticket;
        }
        return null;
    }

    /**
     * Retrieves the count of tickets with an "AVAILABLE" status.
     *
     * @return the number of available tickets
     */
    public long getAvailableTicketCount() {
        long count = ticketRepository.findByStatus("AVAILABLE").size();
        logService.saveHibernateQuery("select count(*) from ticket where status = 'AVAILABLE'");
        return count;
    }
}
