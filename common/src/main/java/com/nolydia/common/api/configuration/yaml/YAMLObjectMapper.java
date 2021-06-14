package com.nolydia.common.api.configuration.yaml;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nolydia.common.api.configuration.ObjectMapper;

import java.io.IOException;
import java.io.InputStream;

public class YAMLObjectMapper implements ObjectMapper {

    private final com.fasterxml.jackson.databind.ObjectMapper mapper;
    private final InputStream inputStream;

    @Inject
    public YAMLObjectMapper(com.fasterxml.jackson.databind.ObjectMapper mapper, @Assisted InputStream inputStream) {
        this.mapper = mapper;
        this.inputStream = inputStream;
    }

    @Override
    public <T> T get(Class<? extends T> targetClass) throws IOException {
        try (inputStream) {
            return mapper.readValue(inputStream, targetClass);
        }
    }
}
