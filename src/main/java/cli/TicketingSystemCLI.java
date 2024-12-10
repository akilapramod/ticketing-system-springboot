package cli;


import java.util.InputMismatchException;
import java.util.Scanner;

import dev.akila.ticketing_system.TicketingSystemApplication;
import dev.akila.ticketing_system.model.Configuration;
import dev.akila.ticketing_system.model.TicketPool;
import dev.akila.ticketing_system.service.ConfigurationService;
import dev.akila.ticketing_system.service.TicketPoolService;
import dev.akila.ticketing_system.threads.Customer;
import dev.akila.ticketing_system.threads.Vendor;
import org.springframework.stereotype.Component;

@Component
public class TicketingSystemCLI {
    private static final Scanner scanner = new Scanner(System.in);
    private static ConfigurationService configurationService = new ConfigurationService();
    private static TicketPoolService ticketPoolService = new TicketPoolService();


    public static void initiateThreads() {

       Configuration cliConfiguration = configurationService.getConfiguration();
        System.out.println(cliConfiguration.toString());
        System.out.println(ticketPoolService.getTicketPool().toString());
        TicketPool ticketPool = ticketPoolService.getTicketPool();


        int numberOfVendors = 2;
        int numberOfCustomers = 2;

        Vendor[] vendors = new Vendor[numberOfVendors];
        Thread[] vendorThreads = new Thread[numberOfVendors];

        Customer[] customers = new Customer[numberOfCustomers];
        Thread[] customerThreads = new Thread[numberOfCustomers];

        // Create and start vendor threads
        for (int i = 0; i < numberOfVendors; i++) {
            vendors[i] = new Vendor(cliConfiguration, ticketPool);
            vendorThreads[i] = new Thread(vendors[i]);
            vendorThreads[i].start();
        }

        // Create and start customer threads
        for (int i = 0; i < numberOfCustomers; i++) {
            customers[i] = new Customer(cliConfiguration, ticketPool);
            customerThreads[i] = new Thread(customers[i]);
            customerThreads[i].start();
        }
    }

    //this method is used to display the menu for the Configuration
    public static void displayConfigurationMenu() {

        /*
         *
         *
         */

        System.out.println("1. Add a new configuration");
        System.out.println("2. load existing configuration");
        System.out.println("0. Exit");
        System.out.print("Enter your choice: ");

        int option;
        while (true) {
            try {

                option = scanner.nextInt();

                switch (option) {
                    case 1:
                        configureSystem();
                        break;
                    case 2:
                        loadConfiguration();
                        break;
                    case 0:
                        exit();
                        break;
                    default:
                        System.out.print("Invalid option. Please enter a number between 0 and 2: ");
                        continue;
                }

                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.nextLine();
            }
        }
    }

    public static void configureSystem() {
        int maxTicketCapacity;
        int totalTickets;
        int ticketReleaseRate;
        int ticketRetrievalRate;


        while (true) {
            try {
                System.out.print("Enter maximum ticket capacity: ");
                maxTicketCapacity = scanner.nextInt();

                if (maxTicketCapacity < 0) {
                    System.out.print("Given input is incorrect. Please enter a positive integer.");
                    continue;
                }

                break;

            } catch (InputMismatchException e) {
                System.out.print("Given input is incorrect. Please Enter a positive number.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.print("Enter total tickets (should be lower than maximum capacity): ");
                totalTickets = scanner.nextInt();

                if (totalTickets <= 0) {
                    System.out.print("Invalid input. Total tickets must be a positive number.");
                    continue;
                }

                if (totalTickets >= maxTicketCapacity) {
                    System.out.print("Invalid input. Total tickets must be less than maximum capacity.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.print("Enter ticket release rate: ");
                ticketReleaseRate = scanner.nextInt();

                if (ticketReleaseRate <= 0) {
                    System.out.println("Invalid input. Release rate must be a positive number.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }
        while (true) {
            try {
                System.out.print("Enter ticket retrieval rate: ");
                ticketRetrievalRate = scanner.nextInt();

                if (ticketRetrievalRate <= 0) {
                    System.out.print("Invalid input. Retrieval rate must be a positive number.");
                    continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid integer.");
                scanner.nextLine();
            }
        }



        configurationService.setConfiguration(totalTickets, maxTicketCapacity, ticketReleaseRate, ticketRetrievalRate);
        ticketPoolService.setTicketPool(totalTickets, maxTicketCapacity);
        System.out.println(configurationService.getConfiguration());

        //CLIconfigurationService.saveSystemConfig(configuration);
    }

    public static void loadConfiguration() {
        ConfigurationService configurationCLI = new ConfigurationService();
        configurationCLI.loadConfigurationFromFile();
        Scanner scanner = new Scanner(System.in);
        while (true) {
            try {
                System.out.println("1. Proceed with the loaded configuration");
                System.out.println("2. Add a new configuration");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        break;
                    case 2:
                        configureSystem();
                        break;
                    case 0:
                        exit();
                        break;
                    default:
                        System.out.print("Invalid option. Please enter a number between 0 and 2: ");
                        continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.nextLine();
            }
        }
        System.out.println(configurationCLI.getConfiguration());
    }

    public static void startSystem() {
        while (true) {
            try {
                System.out.println("1. Start the system");
                System.out.println("0. Exit");
                System.out.print("Enter your choice: ");
                int option = scanner.nextInt();
                switch (option) {
                    case 1:
                        initiateThreads();
                        break;
                    case 0:
                        exit();
                        break;
                    default:
                        System.out.print("Invalid option. Please enter a number between 0 and 1: ");
                        continue;
                }
                break;
            } catch (InputMismatchException e) {
                System.out.print("Invalid input. Please enter a valid number: ");
                scanner.nextLine();
            }
        }
    }

    public static void exit() {
    }

    public static void main(String[] args) {

    }


}