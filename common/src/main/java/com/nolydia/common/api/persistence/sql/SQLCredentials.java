package com.nolydia.common.api.persistence.sql;

// TODO: 04/04/2021 Config les options + ajouter constantes String
public class SQLCredentials {

    private String host;
    private String username;
    private String password;
    private String databaseName;
    private int port;

    public String toURL() {
        return "jdbc:mysql://" + host + ":" + port + "/" + databaseName + "?allowPublicKeyRetrieval=true&useSSL=false";
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}
