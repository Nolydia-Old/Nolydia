package com.nolydia.common.api.configuration;

import com.nolydia.common.api.configuration.exceptions.MapperException;

import java.nio.file.Path;

public interface ObjectMapperFactory {

    ObjectMapper createObjectMapper(Path path) throws MapperException;
}
