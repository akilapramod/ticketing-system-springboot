package dev.akila.ticketing_system.service;

import com.google.gson.Gson;
import dev.akila.ticketing_system.model.Configuration;

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

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
        saveSystemConfig(configuration);
    }

    public Configuration getConfiguration() {
        return configuration;
    }

    public static void saveSystemConfig(Configuration config) {

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

    private static Configuration loadConfigurationFromFile(String filename) {
        if (!new File(filename).exists()) {
            return null;
        }
        Gson gson = new Gson();
        Configuration config = null;
        try (FileReader reader = new FileReader(filename)) {
            config = gson.fromJson(reader, Configuration.class);
        } catch (IOException e) {
            System.err.println("Error loading configuration from file: " + e.getMessage());
        }
        return config;
    }


}
