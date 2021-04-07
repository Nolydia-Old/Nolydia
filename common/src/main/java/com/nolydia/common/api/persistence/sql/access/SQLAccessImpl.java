package com.nolydia.common.api.persistence.sql.access;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nolydia.common.api.persistence.sql.SQLConfiguration;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

public class SQLAccessImpl implements SQLAccess {

    private final SQLConfiguration configuration;
    private HikariDataSource dataSource;

    @Inject
    public SQLAccessImpl(@Named("SQLConfiguration") SQLConfiguration configuration) {
        this.configuration = configuration;
    }

    @Override
    public void initPool() {
        HikariConfig hikariConfiguration = new HikariConfig();

        hikariConfiguration.setJdbcUrl(configuration.toURL());
        hikariConfiguration.setUsername(configuration.getUsername());
        hikariConfiguration.setPassword(configuration.getPassword());

        hikariConfiguration.setMaximumPoolSize(configuration.getPoolSize());

        dataSource = new HikariDataSource(hikariConfiguration);
    }

    @Override
    public void closePool() {
        dataSource.close();
    }

    @Override
    public Connection getConnection() throws SQLException {
        if (isClosed()) {
            initPool();
        }

        return dataSource.getConnection();
    }

    private boolean isClosed() {
        return dataSource == null || !dataSource.isRunning();
    }
}
