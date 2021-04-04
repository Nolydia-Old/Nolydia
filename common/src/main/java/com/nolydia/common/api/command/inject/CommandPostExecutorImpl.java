package com.nolydia.common.api.command.inject;

import com.google.inject.Inject;
import com.nolydia.common.api.command.BaseCommand;
import com.nolydia.common.api.command.Command;
import com.nolydia.common.api.command.CommandRegisterer;
import com.nolydia.common.api.command.repository.CommandRepository;

import java.util.Set;

public class CommandPostExecutorImpl implements CommandPostExecutor {

    private final Set<Command> commands;

    private final CommandRegisterer registerer;
    private final CommandRepository repository;

    @Inject
    public CommandPostExecutorImpl(Set<Command> commands,
                                   CommandRegisterer registerer,
                                   CommandRepository repository) {
        this.commands = commands;
        this.registerer = registerer;
        this.repository = repository;
    }

    @Override
    public void registerCommands() {
        commands
                .forEach(command -> {
                    registerer.registerCommand((BaseCommand) command);
                    repository.addCommand(command);
                });
    }
}
