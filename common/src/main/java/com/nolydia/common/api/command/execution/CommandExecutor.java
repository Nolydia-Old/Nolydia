package com.nolydia.common.api.command.execution;

import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.command.execution.buffer.ArgumentBuffer;

public interface CommandExecutor {

    void execute(CommandSender sender, ArgumentBuffer buffer);
}
