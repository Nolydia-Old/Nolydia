package com.nolydia.common.api.persistence.sql.access;

import java.sql.Connection;
import java.sql.SQLException;

public interface SQLAccess {

    void initPool();

    void closePool();

    Connection getConnection() throws SQLException;
}
