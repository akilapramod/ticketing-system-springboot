package dev.akila.ticketing_system.threads;

import dev.akila.ticketing_system.model.Configuration;
import dev.akila.ticketing_system.model.Ticket;
import dev.akila.ticketing_system.model.TicketPool;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Customer implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(Customer.class);
    private Configuration configuration;
    private TicketPool ticketPool;
    private volatile boolean running = true;


    public Customer(Configuration configuration, TicketPool ticketPool) {

        /*
        This constructor initializes a Customer object with the specified configuration and ticket pool.
        It sets up the customer to interact with the ticketing system, preparing it to purchase tickets
        in a concurrent environment.
        */

        this.configuration = configuration;
        this.ticketPool = ticketPool;
    }

    public void run() {
        /*
        This method executes the customer's ticket purchasing logic. It continuously attempts to purchase tickets
        from the ticket pool at a specified rate, simulating real-world customer behavior in a concurrent environment.
        This method is crucial for demonstrating the consumer side of the Producer-Consumer pattern in the ticketing system.
         */
        while (running) {
            try {
                Ticket ticket = ticketPool.removeTickets();
                logger.info("Customer retrieved ticket: ID {}. Total tickets: {}", ticket.getTicketId(),
                        ticketPool.getAvailableTicketCount());
                Thread.sleep(configuration.getCustomerRetrievalRate());
            } catch (InterruptedException e) {
                logger.error("Thread interrupted", e);
                Thread.currentThread().interrupt(); // Preserve interrupt status
                running = false; // Gracefully stop the thread
            }
        }
    }

    public void stop() {
        running = false;

    }


}
