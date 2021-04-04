package com.nolydia.common.api.command.sender.exceptions;

public class UnsupportedSenderException extends RuntimeException {

    public UnsupportedSenderException() {
        super("Unsupported sender");
    }
}
