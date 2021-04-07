package com.nolydia.common.api.configuration;

import com.nolydia.common.api.configuration.exceptions.MapperException;

public interface ObjectMapper {

    <T> T get(Class<? extends T> targetClass) throws MapperException;
}
