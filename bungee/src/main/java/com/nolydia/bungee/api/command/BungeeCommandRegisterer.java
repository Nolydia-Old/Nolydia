package com.nolydia.bungee.api.command;

import com.google.inject.Inject;
import com.nolydia.common.api.command.BaseCommand;
import com.nolydia.common.api.command.CommandRegisterer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;

public class BungeeCommandRegisterer implements CommandRegisterer {

    private final Plugin plugin;
    private final PluginManager pluginManager;
    private final BungeeCommandMapperFactory mapperFactory;

    @Inject
    public BungeeCommandRegisterer(Plugin plugin, PluginManager pluginManager, BungeeCommandMapperFactory mapperFactory) {
        this.plugin = plugin;
        this.pluginManager = pluginManager;
        this.mapperFactory = mapperFactory;
    }

    @Override
    public void registerCommand(BaseCommand command) {
        pluginManager.registerCommand(plugin, mapperFactory.createBungeeCommandMapper(command));
    }
}
