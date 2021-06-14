package com.nolydia.common.api.configuration;

import java.io.IOException;

public interface ObjectMapper {

    <T> T get(Class<? extends T> targetClass) throws IOException;
}
