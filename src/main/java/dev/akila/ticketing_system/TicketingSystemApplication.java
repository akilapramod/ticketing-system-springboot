/*
    Real-Time
    Event Ticketing System, focusing on the use of Object-
    Oriented Programming (OOP) principles and the Producer-Consumer
    pattern to simulate a dynamic ticketing environment. The system will
    handle concurrent ticket releases and purchases while maintaining data
    integrity.
 */


package dev.akila.ticketing_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TicketingSystemApplication {

    public static void main(String[] args) {

        SpringApplication.run(TicketingSystemApplication.class, args);
        System.out.println("System initiated.");
    }

    public static void logMessage(String s) {
        System.out.println("log");

    }
}
