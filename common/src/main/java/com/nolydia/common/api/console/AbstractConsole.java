package com.nolydia.common.api.console;

import com.nolydia.common.api.command.sender.AbstractCommandSender;
import com.nolydia.common.api.internalization.InternalizationService;
import com.nolydia.common.api.internalization.Locale;

public abstract class AbstractConsole extends AbstractCommandSender implements Console {

    public AbstractConsole(Locale locale, InternalizationService internalizationService) {
        super(locale, internalizationService);
    }

    @Override
    public void setLocale(Locale locale) {
        throw new UnsupportedOperationException("The console's locale cannot be changed");
    }

    @Override
    public boolean hasPermission(String permission) {
        return true;
    }
}
