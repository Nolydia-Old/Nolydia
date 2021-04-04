package com.nolydia.common.api.command;

import com.nolydia.common.api.internalization.InternalizationMessage;

import java.util.Collection;

public class CommandDetails {

    private final String name;
    private final String permission;
    private final Collection<String> aliases;
    private final InternalizationMessage description;

    public CommandDetails(String name,
                          String permission,
                          Collection<String> aliases,
                          InternalizationMessage description) {
        this.name = name;
        this.permission = permission;
        this.aliases = aliases;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getPermission() {
        return permission;
    }

    public Collection<String> getAliases() {
        return aliases;
    }

    public InternalizationMessage getDescription() {
        return description;
    }
}
