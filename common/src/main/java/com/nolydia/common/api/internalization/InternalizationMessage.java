package com.nolydia.common.api.internalization;

public class InternalizationMessage {

    private final String path;
    private final InternalizationArgument[] arguments;

    public InternalizationMessage(String path, InternalizationArgument... arguments) {
        this.path = path;
        this.arguments = arguments;
    }

    public String getPath() {
        return path;
    }

    public InternalizationArgument[] getArguments() {
        return arguments;
    }
}
