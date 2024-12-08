package dev.akila.ticketing_system.threads;

import dev.akila.ticketing_system.TicketingSystemApplication;
import dev.akila.ticketing_system.model.Configuration;
import dev.akila.ticketing_system.model.TicketPool;

public class Customer implements Runnable {
    //    String customerID;
//    String customerName;
    private Configuration configuration;
    private TicketPool ticketPool;
    private volatile boolean running=true;


    public Customer(Configuration configuration, TicketPool ticketPool) {
        //this.customerID = customerID;
        //this.customerName = customerName;
        this.configuration = configuration;
        this.ticketPool = ticketPool;
    }

    public void run() {
        while (running) {
            try {
                ticketPool.removeTickets();
                TicketingSystemApplication.logMessage("A customer has retrieved a ticket. Total tickets: "+ticketPool.getAvailableTicketCount());
                Thread.sleep(configuration.getCustomerRetrievalRate());
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }


    }

    public void stop(){
        running= false;

    }


}
