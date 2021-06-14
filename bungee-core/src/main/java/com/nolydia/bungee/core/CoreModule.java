package com.nolydia.bungee.core;

import com.google.inject.AbstractModule;
import com.nolydia.bungee.core.command.CommandModule;
import com.nolydia.bungee.core.listener.ListenerModule;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new CommandModule());
        install(new ListenerModule());
    }
}
