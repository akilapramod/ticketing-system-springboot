package dev.akila.ticketing_system.controller;

import dev.akila.ticketing_system.model.Configuration;
import dev.akila.ticketing_system.model.TicketPool;
import dev.akila.ticketing_system.service.ConfigurationService;
import dev.akila.ticketing_system.service.TicketPoolService;
import dev.akila.ticketing_system.service.CustomerVendorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/configuration")
public class ConfigurationController {

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketPoolService ticketPoolService;

    @Autowired
    CustomerVendorService customerVendorService;

    @PostMapping("/set")
    public void setConfigurationService(@RequestBody Configuration configuration) {
        configurationService.setConfiguration(configuration);
        ticketPoolService.setTicketPool(new TicketPool(configuration.getTotalTickets(), configuration.getMaxTicketCapacity()));
        System.out.println(configuration.toString());
        System.out.println(ticketPoolService.getTicketPool());
    }

    @PostMapping("/get")
    public Configuration getConfiguration() {
        return configurationService.getConfiguration();
    }

    @PostMapping("/start")
    public void startSystem(){
         customerVendorService.startThreads();
    }

    @PostMapping("stop")
    public void stopSystem(){
        customerVendorService.stopThreads();
        System.out.println("Threads stopped.");
    }

    @PostMapping("load")
    public void loadSystem(){
        Configuration config = configurationService.loadConfigurationFromFile(configurationService.getFilePath());
        //after api get called for loading the configuration, the configuration should be displayed in the frond end
        //and the user should be able to confirm the configuration or edit and set the configuration.
        setConfigurationService(config);

    }

    @PostMapping("/test")
    public void test(){
        System.out.println("HTTP Test");
    }
}
