package dev.akila.ticketing_system.service;

import dev.akila.ticketing_system.model.Configuration;
import dev.akila.ticketing_system.model.TicketPool;
import org.springframework.stereotype.Service;

@Service
public class TicketPoolService {
    private TicketPool ticketPool;

    public void setTicketPool(Configuration configuration) {
        ticketPool = new TicketPool(configuration.getTotalTickets(), configuration.getMaxTicketCapacity());
    }

    public void setTicketPool(int totalTickets, int maxTicketCapacity) {
        ticketPool = new TicketPool(totalTickets, maxTicketCapacity);

    }

    public TicketPool getTicketPool() {
        return ticketPool;
    }

    public int getAvailableTickets() {
        return ticketPool.getAvailableTicketCount();
    }


}

