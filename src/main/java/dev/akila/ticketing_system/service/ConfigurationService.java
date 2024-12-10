package dev.akila.ticketing_system.service;

import com.google.gson.Gson;
import dev.akila.ticketing_system.model.Configuration;

import lombok.Setter;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

@Service
public class ConfigurationService {
    static Logger logger = LoggerFactory.getLogger(ConfigurationService.class);
    private Configuration configuration;
    private final String filePath = "src/main/resources/config.json";


    public ConfigurationService(Configuration configuration){
        this.configuration = configuration;
    }

    public ConfigurationService(){
        this.configuration = configuration;
    }

    //setting the configuration
    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        saveSystemConfig(configuration);
    }

    public void setConfiguration(int totalTickets, int maxTicketCapacity, int ticketReleaseRate, int customerRetrievalRate) {
        configuration = new Configuration(totalTickets,maxTicketCapacity,ticketReleaseRate,customerRetrievalRate);
        saveSystemConfig(configuration);

    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public String getFilePath(){
        return filePath;
    }

    public void saveSystemConfig(Configuration config) {

        String filePath = "src/main/resources/config.json";

        Gson gson = new Gson();

        String json = gson.toJson(config);

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(json);
            logger.info("Configuration saved to {}", filePath);
        } catch (IOException e) {
            logger.error("Error saving configuration", e);
        }
    }

    public Configuration loadConfigurationFromFile() {

        //Need to test and implement further file error handling
        if (!new File(filePath).exists()) {
            return null;
        }
        Gson gson = new Gson();
        Configuration config = null;
        try (FileReader reader = new FileReader(filePath)) {
            config = gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.err.println("Error loading configuration from file: " + e.getMessage());
        }
        return config;
    }
}
