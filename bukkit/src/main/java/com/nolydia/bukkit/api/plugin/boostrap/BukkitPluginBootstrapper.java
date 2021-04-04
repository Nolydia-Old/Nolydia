package com.nolydia.bukkit.api.plugin.boostrap;

import com.google.inject.Injector;
import com.nolydia.bukkit.api.plugin.BukkitPlugin;
import com.nolydia.bukkit.api.plugin.BukkitPluginModule;
import com.nolydia.bukkit.core.CoreModule;
import com.nolydia.common.api.plugin.bootstrap.PluginBootstrapper;

public class BukkitPluginBootstrapper {

    private final BukkitPlugin plugin;

    public BukkitPluginBootstrapper(BukkitPlugin plugin) {
        this.plugin = plugin;
    }

    public Injector bootstrap() {
        return new PluginBootstrapper().boostrap(new BukkitPluginModule(plugin), new CoreModule(), plugin);
    }
}
