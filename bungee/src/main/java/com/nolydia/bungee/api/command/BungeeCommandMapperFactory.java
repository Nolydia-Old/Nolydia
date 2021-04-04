package com.nolydia.bungee.api.command;

import com.nolydia.common.api.command.BaseCommand;

public interface BungeeCommandMapperFactory {

    BungeeCommandMapper createBungeeCommandMapper(BaseCommand command);
}
