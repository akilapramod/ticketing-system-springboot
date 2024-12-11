package dev.akila.ticketing_system.controller;

import dev.akila.ticketing_system.model.Configuration;
import dev.akila.ticketing_system.service.ConfigurationService;
import dev.akila.ticketing_system.service.TicketPoolService;
import dev.akila.ticketing_system.service.CustomerVendorService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    private final ConfigurationService configurationService;
    private final TicketPoolService ticketPoolService;
    final CustomerVendorService customerVendorService;

    public ConfigurationController(ConfigurationService configurationService, TicketPoolService ticketPoolService, CustomerVendorService customerVendorService) {
        this.configurationService = configurationService;
        this.ticketPoolService = ticketPoolService;
        this.customerVendorService = customerVendorService;
    }



    @PostMapping("/set")
    public void setConfigurationService(@RequestBody Configuration configuration) {
        /*
        This method updates the ticketing system's configuration with the provided parameters. It receives a
        Configuration object via a POST request, sets the system's configuration accordingly, and ensures that
        all dependent services are synchronized with the new settings.
        */

        configurationService.setConfiguration(configuration);
        ticketPoolService.setTicketPool(configuration);
    }

    @PostMapping("/get")
    public Configuration getConfiguration() {
        /*
        This method retrieves the current configuration of the ticketing system and returns it as a Configuration object.
        It provides a snapshot of the system's setup, including parameters like total tickets, maximum capacity,
        and release rates.
        */
        return configurationService.getConfiguration();
    }

    @PostMapping("/start")
    public void startSystem() {
         /*
        This method ticketing system's operations via a POST request. It initiates the processes that handle ticket
        distribution and purchase, enabling real-time interactions between vendors and customers. This method is essential
        for remotely starting the system's active phase, where tickets are dynamically managed based on concurrent requests.
        */

        customerVendorService.startThreads();
    }

    @PostMapping("stop")
    public void stopSystem() {
        /*
        This method halts the ticketing system's operations via a POST request. It stops the processes that handle
        ticket distribution and purchase, ensuring a controlled shutdown of the system. This method is crucial for
        remotely stopping the system without data loss or corruption.
        */
        customerVendorService.stopThreads();

    }

    @PostMapping("load")
    public void loadSystem() {
        Configuration config = configurationService.loadConfigurationFromFile();
        /*
        This method loads the system configuration from a persistent storage via a POST request and applies it to
        the current system instance. It retrieves the previously saved configuration parameters and sets up the
        system accordingly.
        */
        setConfigurationService(config);

    }

}
