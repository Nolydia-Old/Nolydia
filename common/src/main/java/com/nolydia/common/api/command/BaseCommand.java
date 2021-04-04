package com.nolydia.common.api.command;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nolydia.common.api.command.argument.Argument;
import com.nolydia.common.api.command.argument.exceptions.ArgumentException;
import com.nolydia.common.api.command.execution.CommandExecutor;
import com.nolydia.common.api.command.execution.buffer.QueueArgumentBuffer;
import com.nolydia.common.api.command.requirement.Condition;
import com.nolydia.common.api.command.requirement.Requirement;
import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.internalization.InternalizationMessage;
import com.nolydia.common.api.internalization.InternalizationService;

import java.util.*;
import java.util.stream.Collectors;

public class BaseCommand implements Command {

    private final InternalizationService internalizationService;

    private BaseCommand parent;

    private final CommandDetails details;
    private final CommandExecutor executor;

    private final Collection<BaseCommand> subCommands;

    private final List<Argument<?>> arguments;
    private final Collection<Requirement> requirements;

    @Inject
    public BaseCommand(InternalizationService internalizationService,
                       @Assisted CommandDetails details,
                       @Assisted CommandExecutor executor) {
        this.internalizationService = internalizationService;

        this.details = details;
        this.executor = executor;

        subCommands = new HashSet<>();
        arguments = new ArrayList<>();
        requirements = new HashSet<>();
    }

    public void execute(CommandSender sender, String[] arguments) {
        if (sender.hasPermission(this.details.getPermission())) {
            if (arguments.length == 0) {
                if (this.subCommands.isEmpty() && !this.arguments.isEmpty() && this.arguments.get(0).isRequired()) {
                    this.sendHelp(sender);
                } else {
                    this.tryExecute(sender, arguments);
                }
            } else {
                Optional<BaseCommand> optionalCommand = this.subCommands.stream()
                        .filter(child ->
                                child.details.getName().equalsIgnoreCase(arguments[0]) || child.details.getAliases().contains(arguments[0]))
                        .findAny();

                optionalCommand.ifPresentOrElse(
                        command -> {
                            if (command.checkRequirements(sender, true)) {
                                String[] newArgs = Arrays.copyOfRange(arguments, 1, arguments.length);

                                command.execute(sender, newArgs);
                            }
                        },
                        () -> {
                            if (this.subCommands.isEmpty()) {
                                long requiredParams = this.arguments.stream()
                                        .filter(Argument::isRequired)
                                        .count();

                                if (this.arguments.stream().anyMatch(Argument::isRequired)) {
                                    if (arguments.length >= requiredParams) {
                                        this.tryExecute(sender, arguments);
                                    } else {
                                        this.sendHelp(sender);
                                    }
                                } else {
                                    if (arguments.length >= requiredParams && arguments.length <= this.arguments.size()) {
                                        this.tryExecute(sender, arguments);
                                    } else {
                                        this.sendHelp(sender);
                                    }
                                }
                            } else {
                                this.sendFullHelp(sender, false);
                            }
                        }
                );
            }
        } else {
            sender.sendMessage(new InternalizationMessage("command.no_permission"));
        }
    }

    public List<String> tabComplete(CommandSender sender, String[] arguments) {
        List<String> tabCompletion = new ArrayList<>();

        if (this.checkRequirements(sender, false)) {
            Optional<BaseCommand> subCommand = this.subCommands.stream()
                    .filter(command ->
                            command.details.getName().equalsIgnoreCase(arguments[0]))
                    .findFirst();

            if (subCommand.isPresent()) {
                BaseCommand command = subCommand.get();
                String[] newArgs = Arrays.copyOfRange(arguments, 1, arguments.length);

                return command.tabComplete(sender, newArgs);
            } else {
                if (!this.subCommands.isEmpty()) {
                    tabCompletion.addAll(this.subCommands.stream()
                            .filter(command ->
                                    command.details.getName().startsWith(arguments[0]) &&
                                            command.checkRequirements(sender, false))
                            .map(command ->
                                    command.details.getName())
                            .collect(Collectors.toList()));
                } else if (!this.arguments.isEmpty()) {
                    long requiredParams = this.arguments.stream().filter(Argument::isRequired).count();

                    if (arguments.length >= requiredParams && arguments.length <= this.arguments.size()) {
                        tabCompletion.addAll(this.arguments.get(arguments.length - 1).tabComplete(sender).stream()
                                .map(Object::toString)
                                .filter(s ->
                                        s.startsWith(arguments[arguments.length - 1]))
                                .collect(Collectors.toList()));
                    }
                }
            }
        }

        Collections.sort(tabCompletion); // Sort the list alphabetically
        return tabCompletion;
    }

    @Override
    public String getHelp(CommandSender sender) {
        BaseCommand parent = this;
        StringBuilder builder = new StringBuilder();

        // Slash sub commands from root
        do {
            builder.append(parent.details.getName()).append(" ");

            parent = parent.parent;
        } while (parent != null);

        List<String> help = Arrays.asList(builder.toString().split(" "));
        Collections.reverse(help);

        builder = new StringBuilder(String.join(" ", help)).append(" ");

        // Params addition
        for (Argument<?> argument : this.arguments) {
            String argumentName = internalizationService.getMessage(sender, argument.getName());
            String format = argument.isRequired() ? "<%s>" : "[%s]";

            builder.append(String.format(format, argumentName)).append(" ");
        }

        // Slash addition
        builder.insert(0, "/");

        return builder.toString();
    }

    @Override
    public void sendHelp(CommandSender sender) {
        sender.sendMessage(getHelp(sender));
    }

    @Override
    public void sendFullHelp(CommandSender sender, boolean verbose) {
        for (BaseCommand child : this.subCommands) {
            if (child.checkRequirements(sender, false)) {
                StringBuilder builder = new StringBuilder(child.getHelp(sender));
                if (verbose) {
                    builder.append(internalizationService.getMessage(sender, child.details.getDescription()));
                }

                sender.sendMessage(builder.toString());
            }
        }
    }

    private void sendRootHelp(CommandSender sender) {
        this.getRoot().sendFullHelp(sender, true);
    }

    private BaseCommand getRoot() {
        if (this.parent == null) {
            return this;
        } else {
            return this.parent.getRoot();
        }
    }

    private boolean checkRequirements(CommandSender sender, boolean verbose) {
        for (Requirement requirement : this.requirements) {
            List<Condition<CommandSender>> conditions = requirement.getConditions();

            for (Condition<CommandSender> condition : conditions) {
                if (condition.abort(sender)) {
                    if (verbose) {
                        sender.sendMessage(condition.getErrorMessage(sender));
                    }

                    return false;
                }
            }
        }

        return true;
    }

    private void tryExecute(CommandSender sender, String[] arguments) {
        if (this.checkRequirements(sender, true)) {
            QueueArgumentBuffer buffer = new QueueArgumentBuffer();

            for (int i = 0; i < arguments.length; i++) {
                Argument<?> argument = this.arguments.get(i);
                String nonParsedArgument = arguments[i];

                if (argument.isString()) {
                    String[] subArguments = Arrays.copyOfRange(arguments, i, arguments.length);

                    nonParsedArgument = String.join(" ", subArguments);
                }

                try {
                    buffer.addArgument(argument.get(sender, nonParsedArgument));
                } catch (ArgumentException e) {
                    sender.sendMessage(e.getErrorMessage());

                    return;
                }

                if (argument.isString()) {
                    break;
                }
            }

            this.executor.execute(sender, buffer);
        }
    }

    @Override
    public String getName() {
        return details.getName();
    }

    @Override
    public Collection<String> getAliases() {
        return details.getAliases();
    }

    @Override
    public void addRequirement(Requirement requirement) {
        requirements.add(requirement);
    }

    @Override
    public void addSubCommand(Command subCommand) {
        if (!arguments.isEmpty()) {
            throw new UnsupportedOperationException("Cannot add a sub command if the command has arguments");
        }

        ((BaseCommand) subCommand).parent = this;
        subCommands.add((BaseCommand) subCommand);
    }

    @Override
    public void addArgument(Argument<?> argument) {
        if (!subCommands.isEmpty()) {
            throw new UnsupportedOperationException("Cannot add an argument if the command has sub commands");
        }

        arguments.add(argument);
    }
}