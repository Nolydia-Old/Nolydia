package com.nolydia.common.api.persistence.sql.exceptions;

import com.nolydia.common.api.persistence.sql.configuration.SQLConfiguration;

import java.sql.SQLException;

public class SQLInitPoolException extends SQLException {

    public SQLInitPoolException(SQLConfiguration configuration, Throwable throwable) {
        super(
                String.format(
                        "An exception occurred during the creation of the SQL pool.\n" +
                                "The SQL configuration used was : %s",
                        configuration.toString()
                ),
                throwable
        );
    }
}
