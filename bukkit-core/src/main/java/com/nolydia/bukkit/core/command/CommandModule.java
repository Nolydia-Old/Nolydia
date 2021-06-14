package com.nolydia.bukkit.core.command;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.multibindings.ProvidesIntoSet;
import com.google.inject.name.Named;
import com.nolydia.bukkit.core.command.lang.LanguageSetCommandExecutor;
import com.nolydia.bukkit.core.command.lang.LanguageShowCommandExecutor;
import com.nolydia.bukkit.core.command.lang.arguments.LocaleArgument;
import com.nolydia.common.api.command.Command;
import com.nolydia.common.api.command.CommandBuilder;
import com.nolydia.common.api.internalization.InternalizationMessage;

public class CommandModule extends AbstractModule {

    @Provides
    @Named("language.set")
    public Command provideLangSetCommand(CommandBuilder builder, LanguageSetCommandExecutor executor, LocaleArgument localeArgument) {
        return builder
                .withName("set")
                .withDescription(new InternalizationMessage("command.language.set"))
                .withExecutor(executor)
                .withArgument(localeArgument)
                .build();
    }

    @Provides
    @Named("language.show")
    public Command provideLangShowCommand(CommandBuilder builder, LanguageShowCommandExecutor executor) {
        return builder
                .withName("show")
                .withDescription(new InternalizationMessage("command.language.show"))
                .withExecutor(executor)
                .build();
    }

    @ProvidesIntoSet
    public Command provideLangCommand(CommandBuilder builder, LanguageShowCommandExecutor executor, @Named("language.set") Command setCommand, @Named("language.show") Command showCommand) {
        return builder
                .withName("language")
                .withAliases("lang")
                .withDescription(new InternalizationMessage("command.language"))
                .withSubCommand(setCommand)
                .withSubCommand(showCommand)
                .withExecutor(executor)
                .build();
    }
}
