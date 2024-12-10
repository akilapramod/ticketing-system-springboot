package dev.akila.ticketing_system.controller;

import dev.akila.ticketing_system.model.Configuration;
import dev.akila.ticketing_system.service.ConfigurationService;
import dev.akila.ticketing_system.service.TicketPoolService;
import dev.akila.ticketing_system.service.CustomerVendorService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
        configurationService.setConfiguration(configuration);
        ticketPoolService.setTicketPool(configuration);
        System.out.println(configuration);
        System.out.println(ticketPoolService.getTicketPool());
    }

    @PostMapping("/get")
    public Configuration getConfiguration() {
        return configurationService.getConfiguration();
    }

    @PostMapping("/start")
    public void startSystem() {
        customerVendorService.startThreads();
    }

    @PostMapping("stop")
    public void stopSystem() {
        customerVendorService.stopThreads();
        System.out.println("Threads stopped.");
    }

    @PostMapping("load")
    public void loadSystem() {
        Configuration config = configurationService.loadConfigurationFromFile();
        /*after api get called for loading the configuration, the configuration should be displayed in the frondend
        and the user should be able to confirm the configuration or edit and set the configuration.
        */
        setConfigurationService(config);

    }

    @PostMapping("/test")
    public void test() {
        System.out.println("HTTP Test");
    }
}
