package com.nolydia.common.api.message;

import net.md_5.bungee.api.chat.BaseComponent;
import net.md_5.bungee.api.chat.TextComponent;

public interface MessageReceiver {

    void sendMessage(BaseComponent[] components);

    default void sendMessage(String message) {
        sendMessage(TextComponent.fromLegacyText(message));
    }
}
