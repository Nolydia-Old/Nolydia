package com.nolydia.common.api.mapping;

import com.nolydia.common.api.mapping.exceptions.MapperException;

import java.nio.file.Path;

public interface ObjectMapperFactory {

    <T> ObjectMapper<T> createObjectMapper(Path path, Class<T> targetClass) throws MapperException;
}
