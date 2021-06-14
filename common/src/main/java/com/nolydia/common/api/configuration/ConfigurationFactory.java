package com.nolydia.common.api.configuration;

import java.io.IOException;
import java.io.InputStream;

public interface ConfigurationFactory {

    Configuration createConfiguration(String name, InputStream inputStream) throws IOException;
}
