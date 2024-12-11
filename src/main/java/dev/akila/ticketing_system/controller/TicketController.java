package dev.akila.ticketing_system.controller;

import dev.akila.ticketing_system.service.TicketPoolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tickets")
public class TicketController {

    @Autowired
    private TicketPoolService ticketPoolService;

    @GetMapping("/available")
    public int getAvailableTickets() {
        /*
        This method retrieves the current number of available tickets from the ticket pool and returns it as an integer.
        It provides real-time information on the system's ticket availability, allowing users to monitor the ticket
        stock dynamically.
        */
        return ticketPoolService.getAvailableTickets();
    }
}