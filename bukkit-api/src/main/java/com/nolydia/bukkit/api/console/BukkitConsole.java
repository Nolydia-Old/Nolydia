package com.nolydia.bukkit.api.console;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nolydia.common.api.console.AbstractConsole;
import com.nolydia.common.api.internalization.InternalizationService;
import com.nolydia.common.api.internalization.Locale;
import net.md_5.bungee.api.chat.BaseComponent;
import org.bukkit.Bukkit;

public class BukkitConsole extends AbstractConsole {

    @Inject
    public BukkitConsole(@Named("DefaultConsoleLocale") Locale locale, InternalizationService internalizationService) {
        super(locale, internalizationService);
    }

    @Override
    public void sendMessage(BaseComponent[] components) {
        Bukkit.getConsoleSender().spigot().sendMessage(components);
    }
}
