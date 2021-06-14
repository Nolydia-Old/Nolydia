package com.nolydia.common.api.persistence.sql.access;

import com.nolydia.common.api.plugin.shutdown.CloseableResource;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLAccess extends CloseableResource {

    void initPool() throws SQLException;

    Connection getConnection() throws SQLException;
}
