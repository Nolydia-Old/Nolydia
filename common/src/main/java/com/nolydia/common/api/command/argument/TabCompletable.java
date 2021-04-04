package com.nolydia.common.api.command.argument;

import com.nolydia.common.api.command.sender.CommandSender;

import java.util.Collection;
import java.util.Collections;

public interface TabCompletable<T> {

    default Collection<T> tabComplete(CommandSender sender) {
        return Collections.emptyList();
    }
}
