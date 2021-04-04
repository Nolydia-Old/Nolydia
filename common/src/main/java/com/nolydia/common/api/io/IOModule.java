package com.nolydia.common.api.io;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.nolydia.common.api.io.directory.FileDirectoryFactory;

public class IOModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .build(FileDirectoryFactory.class));
    }
}
