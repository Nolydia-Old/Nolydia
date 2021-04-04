package com.nolydia.bukkit.core.listener;

import com.google.inject.AbstractModule;
import com.nolydia.common.api.listener.inject.binding.ListenerBinder;
import org.bukkit.event.Listener;

public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        ListenerBinder<Listener> binder = new ListenerBinder<>(binder(), Listener.class);
        binder.addBinding(PlayerJoinListener.class);
    }
}
