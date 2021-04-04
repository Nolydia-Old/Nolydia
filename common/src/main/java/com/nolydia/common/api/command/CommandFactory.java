package com.nolydia.common.api.command;

import com.nolydia.common.api.command.execution.CommandExecutor;

public interface CommandFactory {

    BaseCommand create(CommandDetails details,
                       CommandExecutor executor);
}
