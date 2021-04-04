package com.nolydia.common.api.command;

import com.nolydia.common.api.command.argument.Argument;
import com.nolydia.common.api.command.requirement.Requirement;
import com.nolydia.common.api.command.sender.CommandSender;

import java.util.Collection;

public interface Command {

    void sendFullHelp(CommandSender sender, boolean verbose);

    String getHelp(CommandSender sender);

    void sendHelp(CommandSender sender);

    String getName();

    Collection<String> getAliases();

    void addRequirement(Requirement requirement);

    void addSubCommand(Command command);

    void addArgument(Argument<?> argument);
}
