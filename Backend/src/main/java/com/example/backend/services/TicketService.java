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

    @Autowired
    private LogService logService;

    public void addTickets(int count) {
        for (int i = 0; i < count; i++) {
            Ticket ticket = new Ticket();
            ticket.setStatus("AVAILABLE");
            ticketRepository.save(ticket);
            logService.saveHibernateQuery("insert into ticket (status) values ('AVAILABLE')");
        }
    }

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

    public long getAvailableTicketCount() {
        long count = ticketRepository.findByStatus("AVAILABLE").size();
        logService.saveHibernateQuery("select count(*) from ticket where status = 'AVAILABLE'");
        return count;
    }
}
