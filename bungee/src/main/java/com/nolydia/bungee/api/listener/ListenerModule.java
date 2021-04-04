package com.nolydia.bungee.api.listener;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.nolydia.common.api.listener.inject.ListenerPostExecutor;
import com.nolydia.common.api.listener.inject.ListenerPostExecutorImpl;
import com.nolydia.common.api.listener.ListenerRegisterer;
import net.md_5.bungee.api.plugin.Listener;

public class ListenerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(new TypeLiteral<ListenerRegisterer<Listener>>() {
        }).to(BungeeListenerRegisterer.class);

        bind(ListenerPostExecutor.class).to(new TypeLiteral<ListenerPostExecutorImpl<Listener>>() {
        });
    }
}
