package com.nolydia.bungee.api.command;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nolydia.common.api.command.BaseCommand;
import com.nolydia.common.api.command.sender.CommandSenderProvider;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.plugin.Command;
import net.md_5.bungee.api.plugin.TabExecutor;

public class BungeeCommandMapper extends Command implements TabExecutor {

    private final CommandSenderProvider<CommandSender> commandSenderProvider;
    private final BaseCommand baseCommand;

    @Inject
    public BungeeCommandMapper(CommandSenderProvider<CommandSender> commandSenderProvider,
                               @Assisted BaseCommand baseCommand) {
        super(baseCommand.getName());

        this.commandSenderProvider = commandSenderProvider;
        this.baseCommand = baseCommand;
    }

    @Override
    public void execute(CommandSender sender, String[] args) {
        baseCommand.execute(commandSenderProvider.get(sender), args);
    }

    @Override
    public Iterable<String> onTabComplete(CommandSender sender, String[] args) {
        return baseCommand.tabComplete(commandSenderProvider.get(sender), args);
    }
}
