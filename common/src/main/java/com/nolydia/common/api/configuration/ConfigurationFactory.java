package com.nolydia.common.api.configuration;

import com.nolydia.common.api.configuration.exceptions.ConfigurationException;

import java.nio.file.Path;

public interface ConfigurationFactory {

    Configuration createConfiguration(Path path) throws ConfigurationException;
}
