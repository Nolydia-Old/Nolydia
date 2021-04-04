package com.nolydia.bukkit.api.command;

import com.google.inject.Inject;
import com.nolydia.common.api.command.BaseCommand;
import com.nolydia.common.api.command.CommandRegisterer;
import org.bukkit.Bukkit;
import org.bukkit.command.CommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

public class BukkitCommandRegisterer implements CommandRegisterer {

    private final BukkitCommandMapperFactory mapperFactory;

    private final CommandMap commandMap;

    @Inject
    public BukkitCommandRegisterer(BukkitCommandMapperFactory mapperFactory) throws Exception {
        this.mapperFactory = mapperFactory;

        Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
        commandMapField.setAccessible(true);

        PluginManager pluginManager = Bukkit.getServer().getPluginManager();
        commandMap = (CommandMap) commandMapField.get(pluginManager);

        commandMap.clearCommands();
    }

    @Override
    public void registerCommand(BaseCommand command) {
        commandMap.register("", mapperFactory.createBukkitCommandMapper(command));
    }
}
