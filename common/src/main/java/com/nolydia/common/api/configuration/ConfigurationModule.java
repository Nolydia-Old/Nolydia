package com.nolydia.common.api.configuration;

import com.fasterxml.jackson.dataformat.yaml.YAMLFactory;
import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.name.Named;
import com.nolydia.common.api.configuration.yaml.YAMLConfiguration;
import com.nolydia.common.api.configuration.yaml.YAMLObjectMapper;
import org.yaml.snakeyaml.Yaml;

import java.nio.file.Path;

public class ConfigurationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Yaml.class).toInstance(new Yaml());
        bind(com.fasterxml.jackson.databind.ObjectMapper.class).toInstance(new com.fasterxml.jackson.databind.ObjectMapper(new YAMLFactory()));

        install(new FactoryModuleBuilder()
                .implement(Configuration.class, YAMLConfiguration.class)
                .build(ConfigurationFactory.class));

        install(new FactoryModuleBuilder()
                .implement(ObjectMapper.class, YAMLObjectMapper.class)
                .build(ObjectMapperFactory.class));
    }

    @Provides
    @Named("ConfigurationPath")
    public Path provideConfigurationPath() {
        return Path.of("/users/juan/nolydia/configuration");
    }
}
