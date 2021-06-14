package com.nolydia.bungee.api.listener;

import com.google.inject.Inject;
import com.nolydia.common.api.listener.ListenerRegisterer;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeListenerRegisterer implements ListenerRegisterer<Listener> {

    private final Plugin plugin;
    private final PluginManager pluginManager;

    @Inject
    public BungeeListenerRegisterer(Plugin plugin, PluginManager pluginManager) {
        this.plugin = plugin;
        this.pluginManager = pluginManager;
    }

    @Override
    public void registerListener(Listener listener) {
        pluginManager.registerListener(plugin, listener);
    }
}
