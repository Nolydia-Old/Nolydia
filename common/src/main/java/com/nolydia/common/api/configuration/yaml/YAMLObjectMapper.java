package com.nolydia.common.api.configuration.yaml;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nolydia.common.api.configuration.ObjectMapper;
import com.nolydia.common.api.configuration.exceptions.MapperException;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class YAMLObjectMapper implements ObjectMapper {

    private final com.fasterxml.jackson.databind.ObjectMapper mapper;
    private final Path path;

    @Inject
    public YAMLObjectMapper(com.fasterxml.jackson.databind.ObjectMapper mapper, @Assisted Path path) {
        this.mapper = mapper;
        this.path = path;
    }

    @Override
    public <T> T get(Class<? extends T> targetClass) throws MapperException {
        try (InputStream in = Files.newInputStream(path)){
            return mapper.readValue(in, targetClass);
        } catch (Exception e) {
            throw new MapperException(e);
        }
    }
}
