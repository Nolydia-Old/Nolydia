package com.nolydia.common.api.persistence.sql.access;

import com.google.inject.Inject;
import com.nolydia.common.api.persistence.sql.configuration.SQLConfiguration;
import com.nolydia.common.api.persistence.sql.configuration.SQLConfigurationProvider;
import com.nolydia.common.api.persistence.sql.configuration.UnavailableSQLConfiguration;
import com.nolydia.common.api.persistence.sql.exceptions.SQLInitPoolException;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class SQLAccessImpl implements SQLAccess {

    private final SQLConfiguration configuration;
    private HikariDataSource dataSource;

    @Inject
    public SQLAccessImpl(SQLConfigurationProvider configurationProvider) {
        SQLConfiguration configuration;

        try {
            configuration = configurationProvider.get();
        } catch (IOException e) {
            e.printStackTrace();
            configuration = UnavailableSQLConfiguration.getDefaultConfiguration();
        }

        this.configuration = configuration;
    }

    @Override
    public void initPool() throws SQLInitPoolException {
        HikariConfig hikariConfiguration = new HikariConfig();

        hikariConfiguration.setJdbcUrl(configuration.toURL());
        hikariConfiguration.setUsername(configuration.getUsername());
        hikariConfiguration.setPassword(configuration.getPassword());

        hikariConfiguration.setMaximumPoolSize(configuration.getPoolSize());

        try {
            dataSource = new HikariDataSource(hikariConfiguration);
        } catch (Exception e) {
            throw new SQLInitPoolException(configuration, e);
        }
    }

    @Override
    public void close() {
        if (!isClosed()) {
            dataSource.close();
        }
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (isClosed()) {
            initPool();
        }

        return dataSource.getConnection();
    }

    private boolean isClosed() {
        return dataSource == null || dataSource.isClosed();
    }
}
