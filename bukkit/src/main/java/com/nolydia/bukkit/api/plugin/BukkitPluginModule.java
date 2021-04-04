package com.nolydia.bukkit.api.plugin;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.nolydia.bukkit.api.command.CommandModule;
import com.nolydia.bukkit.api.console.BukkitConsole;
import com.nolydia.bukkit.api.listener.ListenerModule;
import com.nolydia.common.api.console.Console;
import com.nolydia.common.api.plugin.annotations.DataFolder;
import com.nolydia.common.api.plugin.annotations.Logging;
import com.nolydia.common.api.scheduler.Scheduler;
import org.bukkit.Server;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.PluginManager;

import java.nio.file.Path;
import java.util.logging.Logger;

public class BukkitPluginModule extends AbstractModule {

    private final Plugin plugin;

    public BukkitPluginModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);
        bind(Server.class).toInstance(plugin.getServer());
        bind(PluginManager.class).toInstance(plugin.getServer().getPluginManager());
        bind(org.bukkit.scheduler.BukkitScheduler.class).toInstance(plugin.getServer().getScheduler());

        bind(Console.class).to(BukkitConsole.class);

        bind(Scheduler.class).to(com.nolydia.bukkit.api.scheduler.BukkitScheduler.class);

        install(new CommandModule());
        install(new ListenerModule());
    }

    @Provides
    @DataFolder
    public Path provideDataFolder() {
        return Path.of("servers").resolve(plugin.getName());
    }

    @Provides
    @Logging
    public Logger provideLogger() {
        return plugin.getLogger();
    }
}
