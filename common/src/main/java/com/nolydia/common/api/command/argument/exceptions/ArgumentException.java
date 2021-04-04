package com.nolydia.common.api.command.argument.exceptions;

import com.nolydia.common.api.internalization.InternalizationMessage;

public class ArgumentException extends RuntimeException {

    private final InternalizationMessage message;

    public ArgumentException(InternalizationMessage message) {
        super("Bad argument's use exception");

        this.message = message;
    }

    public InternalizationMessage getErrorMessage() {
        return message;
    }
}
