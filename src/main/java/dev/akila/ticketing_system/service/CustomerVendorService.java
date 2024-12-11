package dev.akila.ticketing_system.service;


import dev.akila.ticketing_system.model.Configuration;
import dev.akila.ticketing_system.model.TicketPool;
import dev.akila.ticketing_system.threads.Customer;
import dev.akila.ticketing_system.threads.Vendor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerVendorService {
    static Logger logger = LoggerFactory.getLogger(ConfigurationService.class);

    @Autowired
    private ConfigurationService configurationService;

    @Autowired
    private TicketPoolService ticketPoolService;

    int numberOfVendors = 1;
    int numberCustomers = 4;

    Vendor[] vendors = new Vendor[numberOfVendors];
    Customer[] customers = new Customer[numberCustomers];

    public void startThreads() {
        Configuration configuration = configurationService.getConfiguration();
        TicketPool ticketPool = ticketPoolService.getTicketPool();

        Thread[] vendorThreads = new Thread[numberOfVendors];
        Thread[] customerThreads = new Thread[numberCustomers];

        /*
        this creates vendoran objects in the with conigiration parameters
         */

        for (int i = 0; i < numberOfVendors; i++) {
            vendors[i] = new Vendor(configuration, ticketPool);
            vendorThreads[i] = new Thread(vendors[i]);
            vendorThreads[i].start();
        }

        for (int i = 0; i < numberCustomers; i++) {
            customers[i] = new Customer(configuration, ticketPool);
            customerThreads[i] = new Thread(customers[i]);
            customerThreads[i].start();
        }

    }

    public void stopThreads(){
        if (vendors != null) {
            for (Vendor vendor : vendors) {
                if (vendor != null) {
                    vendor.stop();
                }
            }

        }

        if (customers != null) {
            for (Customer customer : customers) {
                if (customer != null) {
                    customer.stop();
                }

            }

        }
        logger.info("Ticketing system stopped.");
    }
}
