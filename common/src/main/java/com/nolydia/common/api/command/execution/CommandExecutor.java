package com.nolydia.common.api.command.execution;

import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.command.execution.buffer.ArgumentBuffer;

/**
 * This interface can be implemented to define the behavior of a command after its execution.
 * <p>
 * No condition other than those already existing should be defined.
 */
public interface CommandExecutor {

    /**
     * Calls when the command is executed.
     *
     * @param sender
     * @param buffer
     */
    void execute(CommandSender sender, ArgumentBuffer buffer);
}
