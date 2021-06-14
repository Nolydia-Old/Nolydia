package com.nolydia.bukkit.api.command;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nolydia.common.api.command.BaseCommand;
import com.nolydia.common.api.command.CommandRegisterer;
import org.bukkit.command.CommandMap;
import org.bukkit.command.SimpleCommandMap;
import org.bukkit.plugin.PluginManager;
import org.bukkit.plugin.SimplePluginManager;

import java.lang.reflect.Field;

public class BukkitCommandRegisterer implements CommandRegisterer {

    private final String pluginName;
    private final BukkitCommandMapperFactory commandMapperFactory;

    private final CommandMap commandMap;

    @Inject
    public BukkitCommandRegisterer(@Named("PluginName") String pluginName, BukkitCommandMapperFactory commandMapperFactory, PluginManager pluginManager) throws Exception {
        this.pluginName = pluginName;
        this.commandMapperFactory = commandMapperFactory;

        Field commandMapField = SimplePluginManager.class.getDeclaredField("commandMap");
        commandMapField.setAccessible(true);
        commandMap = (SimpleCommandMap) commandMapField.get(pluginManager);
        commandMapField.setAccessible(false);
    }

    @Override
    public void registerCommand(BaseCommand command) {
        commandMap.register(pluginName, commandMapperFactory.createBukkitCommandMapper(command));
    }
}
