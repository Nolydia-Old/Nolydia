package com.nolydia.bukkit.core;

import com.google.inject.AbstractModule;
import com.nolydia.bukkit.core.command.CommandModule;
import com.nolydia.bukkit.core.listener.ListenerModule;

public class CoreModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new CommandModule());
        install(new ListenerModule());
    }
}
