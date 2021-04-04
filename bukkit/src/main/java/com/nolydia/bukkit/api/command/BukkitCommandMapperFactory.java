package com.nolydia.bukkit.api.command;

import com.nolydia.common.api.command.BaseCommand;

public interface BukkitCommandMapperFactory {

    BukkitCommandMapper createBukkitCommandMapper(BaseCommand command);
}
