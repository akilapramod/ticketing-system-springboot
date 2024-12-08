package dev.akila.ticketing_system.service;

import dev.akila.ticketing_system.model.TicketPool;
import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {
    private TicketPool ticketPool;

    public void setTicketPool(TicketPool ticketPool) {
        this.ticketPool = ticketPool;
    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public int getAvailableTickets() {
        return ticketPool.getAvailableTicketCount();
    }
}

