package com.nolydia.bungee.api.plugin;

import com.google.inject.AbstractModule;
import com.google.inject.name.Names;
import com.nolydia.bungee.api.command.CommandModule;
import com.nolydia.bungee.api.console.BungeeConsole;
import com.nolydia.bungee.api.listener.ListenerModule;
import com.nolydia.bungee.api.scheduler.BungeeScheduler;
import com.nolydia.common.api.console.Console;
import com.nolydia.common.api.plugin.annotations.DataFolder;
import com.nolydia.common.api.plugin.annotations.Logging;
import com.nolydia.common.api.scheduler.Scheduler;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.plugin.PluginManager;
import net.md_5.bungee.api.scheduler.TaskScheduler;

import java.nio.file.Path;
import java.util.logging.Logger;

public class BungeePluginModule extends AbstractModule {

    private final Plugin plugin;

    public BungeePluginModule(Plugin plugin) {
        this.plugin = plugin;
    }

    @Override
    protected void configure() {
        bind(Plugin.class).toInstance(plugin);
        bind(ProxyServer.class).toInstance(plugin.getProxy());
        bind(PluginManager.class).toInstance(plugin.getProxy().getPluginManager());
        bind(TaskScheduler.class).toInstance(plugin.getProxy().getScheduler());

        bind(Path.class).annotatedWith(DataFolder.class).toInstance(plugin.getDataFolder().toPath());
        bind(Logger.class).annotatedWith(Logging.class).toInstance(plugin.getLogger());

        bind(CommandSender.class).annotatedWith(Names.named("BungeeConsole")).toInstance(plugin.getProxy().getConsole());

        bind(Console.class).to(BungeeConsole.class);

        bind(Scheduler.class).to(BungeeScheduler.class);

        install(new CommandModule());
        install(new ListenerModule());
    }
}
