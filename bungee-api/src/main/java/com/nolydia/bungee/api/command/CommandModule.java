package com.nolydia.bungee.api.command;

import com.google.inject.AbstractModule;
import com.google.inject.TypeLiteral;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.nolydia.bungee.api.command.sender.BungeeCommandSenderProvider;
import com.nolydia.common.api.command.CommandRegisterer;
import com.nolydia.common.api.command.sender.CommandSenderProvider;
import net.md_5.bungee.api.CommandSender;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(CommandRegisterer.class).to(BungeeCommandRegisterer.class);

        bind(new TypeLiteral<CommandSenderProvider<CommandSender>>() {}).to(BungeeCommandSenderProvider.class);

        install(new FactoryModuleBuilder()
                .build(BungeeCommandMapperFactory.class));
    }
}
