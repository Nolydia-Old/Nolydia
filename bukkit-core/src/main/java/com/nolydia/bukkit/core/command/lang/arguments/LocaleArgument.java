package com.nolydia.bukkit.core.command.lang.arguments;

import com.nolydia.common.api.command.argument.exceptions.ArgumentException;
import com.nolydia.common.api.command.argument.Argument;
import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.internalization.InternalizationArgument;
import com.nolydia.common.api.internalization.InternalizationMessage;
import com.nolydia.common.api.internalization.Locale;

import java.util.Optional;

public class LocaleArgument implements Argument<Locale> {

    @Override
    public InternalizationMessage getName() {
        return new InternalizationMessage("argument.locale");
    }

    @Override
    public Locale get(CommandSender sender, String argument) {
        Optional<Locale> optionalLocale = Locale.getByName(argument);

        if (optionalLocale.isEmpty()) {
            throw new ArgumentException(new InternalizationMessage("argument.locale.invalid_locale", new InternalizationArgument("locale", argument)));
        }

        Locale locale = optionalLocale.get();

        if (locale == sender.getLocale()) {
            throw new ArgumentException(new InternalizationMessage("argument.locale.has_already_locale", new InternalizationArgument("locale", locale)));
        }

        return locale;
    }
}
