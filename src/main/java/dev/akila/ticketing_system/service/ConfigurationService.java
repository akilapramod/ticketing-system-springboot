package dev.akila.ticketing_system.service;

import dev.akila.ticketing_system.model.Configuration;
import org.springframework.stereotype.Service;

@Service
public class ConfigurationService {
    private Configuration configuration;

    public void setConfiguration(Configuration configuration) {
        this.configuration = configuration;
    }

    public Configuration getConfiguration(){
        return configuration;
    }
}
