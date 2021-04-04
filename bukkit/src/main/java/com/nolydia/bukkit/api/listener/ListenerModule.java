package com.nolydia.bukkit.api.listener;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.nolydia.common.api.listener.inject.ListenerPostExecutor;
import com.nolydia.common.api.listener.inject.ListenerPostExecutorImpl;
import com.nolydia.common.api.listener.ListenerRegisterer;
import org.bukkit.event.Listener;

public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<ListenerRegisterer<Listener>>() {
        }).to(BukkitListenerRegisterer.class);

        bind(ListenerPostExecutor.class).to(new TypeLiteral<ListenerPostExecutorImpl<Listener>>() {
        });
    }
}
