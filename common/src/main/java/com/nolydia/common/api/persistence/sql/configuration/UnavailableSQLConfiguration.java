package com.nolydia.common.api.persistence.sql.configuration;

import java.util.Map;

public class UnavailableSQLConfiguration {

    public static SQLConfiguration getDefaultConfiguration() {
        SQLConfiguration configuration = new SQLConfiguration();

        configuration.setHost("localhost");
        configuration.setPort(3306);
        configuration.setUsername("root");
        configuration.setPassword("root");
        configuration.setDatabase("Nolydia");
        configuration.setPoolSize(1);
        configuration.setOptions(Map.of("useSSL", "false"));

        return configuration;
    }
}
