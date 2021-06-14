package com.nolydia.common.api.persistence.sql.configuration;

import java.util.Map;
import java.util.StringJoiner;

@SuppressWarnings("unused") // Reflective access
public class SQLConfiguration {

    private static final String DRIVER_FORMAT = "jdbc:mysql://%s:%d/%s";

    private String host;
    private int port;
    private String username;
    private String password;
    private String database;
    private int poolSize;
    private Map<String, String> options;

    public String toURL() {
        StringBuilder urlBuilder = new StringBuilder(String.format(DRIVER_FORMAT, host, port, database));

        if (!options.isEmpty()) {
            StringJoiner joiner = new StringJoiner("&", "?", "");
            options.forEach((k, v) -> joiner.add(String.format("%s=%s", k, v)));
            urlBuilder.append(joiner);
        }

        return urlBuilder.toString();
    }

    public void setHost(String host) {
        this.host = host;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDatabase(String database) {
        this.database = database;
    }

    public void setPoolSize(int poolSize) {
        this.poolSize = poolSize;
    }

    public int getPoolSize() {
        return poolSize;
    }

    public void setOptions(Map<String, String> options) {
        this.options = options;
    }

    @Override
    public String toString() {
        return "SQLConfiguration{" +
                "host='" + host + '\'' +
                ", port=" + port +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", database='" + database + '\'' +
                ", poolSize=" + poolSize +
                ", options=" + options +
                '}';
    }
}
