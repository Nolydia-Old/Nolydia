package com.nolydia.bukkit.core.command.lang;

import com.nolydia.common.api.command.execution.CommandExecutor;
import com.nolydia.common.api.command.execution.buffer.ArgumentBuffer;
import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.internalization.Locale;

public class LanguageSetCommandExecutor implements CommandExecutor {

    @Override
    public void execute(CommandSender sender, ArgumentBuffer buffer) {
        Locale locale = buffer.readArgument();
        sender.setLocale(locale);
    }
}
