package com.nolydia.bungee.core.listener;

import com.google.inject.AbstractModule;
import com.nolydia.common.api.listener.inject.binding.ListenerBinder;
import net.md_5.bungee.api.plugin.Listener;

public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        ListenerBinder<Listener> binder = new ListenerBinder<>(binder(), Listener.class);
        binder.addBinding(PlayerLoginListener.class);
    }
}
