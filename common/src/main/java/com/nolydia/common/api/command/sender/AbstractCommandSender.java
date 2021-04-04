package com.nolydia.common.api.command.sender;

import com.nolydia.common.api.internalization.InternalizationMessage;
import com.nolydia.common.api.internalization.InternalizationService;
import com.nolydia.common.api.internalization.Locale;

public abstract class AbstractCommandSender implements CommandSender {

    private final Locale locale;
    private final InternalizationService internalizationService;

    public AbstractCommandSender(Locale locale, InternalizationService internalizationService) {
        this.locale = locale;
        this.internalizationService = internalizationService;
    }

    @Override
    public Locale getLocale() {
        return locale;
    }

    @Override
    public void sendMessage(InternalizationMessage message) {
        sendMessage(internalizationService.getMessage(this, message));
    }
}
