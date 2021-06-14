package com.nolydia.bungee.core.listener;

import com.google.inject.AbstractModule;
import com.google.inject.multibindings.Multibinder;
import net.md_5.bungee.api.plugin.Listener;

public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        Multibinder<Listener> binder = Multibinder.newSetBinder(binder(), Listener.class);
        binder.addBinding().to(PlayerLoginListener.class);
    }
}
