package com.nolydia.common.api.mapping;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.nolydia.common.api.mapping.yaml.YAMLObjectMapper;

public class MappingModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .implement(ObjectMapper.class, YAMLObjectMapper.class)
                .build(ObjectMapperFactory.class));
    }
}
