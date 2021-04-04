package com.nolydia.common.api.utils.yaml;

import com.google.inject.AbstractModule;

public class YAMLModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(YAMLFactory.class).to(YAMLFactoryImpl.class);
    }
}
