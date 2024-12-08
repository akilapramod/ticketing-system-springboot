package dev.akila.ticketing_system.model;//TicketPool class using a thread-safe data structure

import java.math.BigDecimal;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;


public class TicketPool {
    private LinkedList<Ticket> ticketsList;
    private int maximumTicketCapacity;
    private int availableTicketCount;

    public TicketPool(int availableTicketCount, int maximumTicketCapacity) {
        this.maximumTicketCapacity = maximumTicketCapacity;
        this.availableTicketCount = availableTicketCount;
        this.ticketsList = new LinkedList<>();
        for (int i = 0; i < availableTicketCount; i++) {
            Ticket ticket = new Ticket("Event " + (i + 1), new BigDecimal(10.0));
            ticketsList.add(ticket);
        }
    }

    //Getters for the TicketPool

    //get available tickets
    public int getAvailableTicketCount() {
        return ticketsList.size();
    }

    public synchronized void addTickets(Ticket ticket) {
        while (ticketsList.size() >= maximumTicketCapacity) {
            try {
                wait();
                System.out.println("Ticket Pool is full");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted Exception");
                return;
            }
        }
        ticketsList.add(ticket);
        System.out.println("ticket added.");
        notifyAll();
    }

    public synchronized void removeTickets() {
        while (ticketsList.isEmpty()) {
            try {
                System.out.println("Ticket Pool is empty");
                wait(); //wait if the ticket pool is empty
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.out.println("Interrupted Exception");
                return;
            }
        }
        ticketsList.removeFirst();
        System.out.println("Ticket removed");
        notifyAll();
    }


}

