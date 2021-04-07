package com.nolydia.common.api.configuration.exceptions;

public class MapperException extends Exception {

    public MapperException(Throwable cause) {
        super("Exception occurred during the mapper creation", cause);
    }
}
