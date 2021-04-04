package com.nolydia.common.api.configuration;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.nolydia.common.api.configuration.yaml.YAMLConfiguration;
import org.yaml.snakeyaml.Yaml;

public class ConfigurationModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(Yaml.class).toInstance(new Yaml());

        install(new FactoryModuleBuilder()
                .implement(Configuration.class, YAMLConfiguration.class)
                .build(ConfigurationFactory.class));
    }
}
