package dev.akila.ticketing_system.threads;

import dev.akila.ticketing_system.TicketingSystemApplication;
import dev.akila.ticketing_system.model.Configuration;
import dev.akila.ticketing_system.model.Ticket;
import dev.akila.ticketing_system.model.TicketPool;

import java.math.BigDecimal;

public class Vendor implements Runnable {
    private Configuration configuration;
    private TicketPool ticketPool;
    private volatile boolean running = true;

    public Vendor(Configuration configuration, TicketPool ticketPool) {
        this.configuration = configuration;
        this.ticketPool = ticketPool;
    }

    @Override
    public void run() {
        int ticketId = 1;

        while (running) {
            try {

                Ticket newTicket = new Ticket("Event " + ticketId, new BigDecimal(10.0));
                ticketPool.addTickets(newTicket);
                TicketingSystemApplication.logMessage("A vendor has added a ticket. Total tickets: " + ticketPool.getAvailableTicketCount());
                Thread.sleep(configuration.getTicketReleaseRate());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }

        }
    }

    public void stop() {
        running = false;
    }
}
