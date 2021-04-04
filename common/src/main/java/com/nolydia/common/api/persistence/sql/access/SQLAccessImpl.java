package com.nolydia.common.api.persistence.sql.access;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nolydia.common.api.persistence.sql.SQLCredentials;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;

// TODO: 04/04/2021 Mettre en config
public class SQLAccessImpl implements SQLAccess {

    private final SQLCredentials credentials;
    private HikariDataSource dataSource;

    @Inject
    public SQLAccessImpl(@Named("SQLCredentials") SQLCredentials credentials) {
        this.credentials = credentials;
    }

    @Override
    public void initPool() {
        HikariConfig config = new HikariConfig();

        config.setJdbcUrl(credentials.toURL());
        config.setUsername(credentials.getUsername());
        config.setPassword(credentials.getPassword());

        config.setMaximumPoolSize(16);

        dataSource = new HikariDataSource(config);
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
