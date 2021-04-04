package com.nolydia.common.api.configuration.exceptions;

import com.nolydia.common.api.configuration.Configuration;

/**
 * Wraps an exception occurred during the creation of a {@link Configuration}
 */
public class ConfigurationException extends Exception {

    public ConfigurationException(Throwable cause) {
        super("Exception occurred during the creation of a configuration", cause);
    }
}
