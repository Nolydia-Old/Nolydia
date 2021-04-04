package com.nolydia.common.api.message;

public interface Message {

    void send(MessageReceiver sender);

    String getContent();
}
