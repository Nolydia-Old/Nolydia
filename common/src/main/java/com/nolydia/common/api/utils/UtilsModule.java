package com.nolydia.common.api.utils;

import com.google.inject.AbstractModule;
import com.nolydia.common.api.utils.yaml.YAMLModule;

public class UtilsModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new YAMLModule());
    }
}
