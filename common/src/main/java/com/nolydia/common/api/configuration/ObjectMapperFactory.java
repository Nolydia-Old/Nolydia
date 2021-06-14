package com.nolydia.common.api.configuration;

import java.io.IOException;
import java.io.InputStream;

public interface ObjectMapperFactory {

    ObjectMapper createObjectMapper(InputStream inputStream) throws IOException;
}
