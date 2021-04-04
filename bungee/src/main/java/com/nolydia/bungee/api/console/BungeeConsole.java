package com.nolydia.bungee.api.console;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nolydia.common.api.console.AbstractConsole;
import com.nolydia.common.api.internalization.InternalizationService;
import com.nolydia.common.api.internalization.Locale;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.chat.BaseComponent;

public class BungeeConsole extends AbstractConsole {

    private final ProxyServer proxyServer;

    @Inject
    public BungeeConsole(@Named("DefaultConsoleLocale") Locale locale, InternalizationService internalizationService, ProxyServer proxyServer) {
        super(locale, internalizationService);

        this.proxyServer = proxyServer;
    }

    @Override
    public void sendMessage(BaseComponent[] components) {
        proxyServer.getConsole().sendMessage(components);
    }
}
