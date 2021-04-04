package com.nolydia.common.api.command.sender;

public interface CommandSenderProvider<T> {

    CommandSender get(T sender);
}
