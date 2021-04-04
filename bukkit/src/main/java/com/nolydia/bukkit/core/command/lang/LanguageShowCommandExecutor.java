package com.nolydia.bukkit.core.command.lang;

import com.nolydia.common.api.command.execution.CommandExecutor;
import com.nolydia.common.api.command.execution.buffer.ArgumentBuffer;
import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.internalization.InternalizationArgument;
import com.nolydia.common.api.internalization.InternalizationMessage;

public class LanguageShowCommandExecutor implements CommandExecutor {

    @Override
    public void execute(CommandSender sender, ArgumentBuffer buffer) {
        sender.sendMessage(new InternalizationMessage("display_locale", new InternalizationArgument("locale", sender.getLocale())));
    }
}
