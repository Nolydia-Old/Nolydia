package com.nolydia.common.api.server;

import com.google.inject.AbstractModule;

public class ServerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(ServerRepository.class).to(PluginServerRepository.class);
    }
}
