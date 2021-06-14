package com.nolydia.bungee.api.plugin.bootstrap;

import com.google.inject.Injector;
import com.nolydia.bungee.api.plugin.BungeePlugin;
import com.nolydia.bungee.api.plugin.BungeePluginModule;
import com.nolydia.common.api.plugin.bootstrap.PluginBootstrapper;

public class BungeePluginBootstrapper {

    private final BungeePlugin plugin;

    public BungeePluginBootstrapper(BungeePlugin plugin) {
        this.plugin = plugin;
    }

    public Injector bootstrap() throws Exception {
        return new PluginBootstrapper().boostrap(new BungeePluginModule(plugin), plugin);
    }
}
