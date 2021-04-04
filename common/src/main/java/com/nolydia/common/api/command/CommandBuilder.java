package com.nolydia.common.api.command;

import com.google.inject.Inject;
import com.nolydia.common.api.command.argument.Argument;
import com.nolydia.common.api.command.execution.CommandExecutor;
import com.nolydia.common.api.command.requirement.Requirement;
import com.nolydia.common.api.internalization.InternalizationArgument;
import com.nolydia.common.api.internalization.InternalizationMessage;

import java.util.*;

public class CommandBuilder {

    private final CommandFactory factory;

    private String name;

    private String permission;
    private final Collection<String> aliases;
    private InternalizationMessage description;

    private final List<Requirement> requirements;
    private final List<Command> subCommands;
    private final List<Argument<?>> arguments;

    private CommandExecutor executor;

    @Inject
    public CommandBuilder(CommandFactory factory) {
        this.factory = factory;

        aliases = new HashSet<>();

        requirements = new ArrayList<>();
        arguments = new ArrayList<>();
        subCommands = new ArrayList<>();
    }

    public CommandBuilder withName(String name) {
        this.name = name;

        return this;
    }

    public CommandBuilder withPermission(String permission) {
        this.permission = permission;

        return this;
    }

    public CommandBuilder withAliases(String... aliases) {
        Collections.addAll(this.aliases, aliases);

        return this;
    }

    public CommandBuilder withDescription(InternalizationMessage description) {
        this.description = description;

        return this;
    }

    public CommandBuilder withRequirement(Requirement requirement) {
        this.requirements.add(requirement);

        return this;
    }

    public CommandBuilder withArgument(Argument<?> argument) {
        this.arguments.add(argument);

        return this;
    }

    public CommandBuilder withSubCommand(Command subCommand) {
        subCommands.add(subCommand);

        return this;
    }

    public CommandBuilder withExecutor(CommandExecutor executor) {
        this.executor = executor;

        return this;
    }

    public Command build() {
        // todo vérifications, executor par défaut

        Command command = factory.create(
                new CommandDetails(
                        name,
                        permission,
                        aliases,
                        description
                ),
                executor);

        requirements.forEach(command::addRequirement);
        subCommands.forEach(command::addSubCommand);
        arguments.forEach(command::addArgument);

        if (!subCommands.isEmpty()) {
            // If the command has sub commands, then we add a default help sub command

            Command helpCommand = factory.create(
                    new CommandDetails(
                            "help",
                            permission,
                            Collections.emptySet(),
                            new InternalizationMessage("command.help", new InternalizationArgument("command", name))
                    ),
                    (sender, buffer) -> {
                        sender.sendMessage(new InternalizationMessage("display_help", new InternalizationArgument("command", name)));
                        command.sendFullHelp(sender, false);
                    }
            );

            command.addSubCommand(helpCommand);
        }

        return command;
    }
}
