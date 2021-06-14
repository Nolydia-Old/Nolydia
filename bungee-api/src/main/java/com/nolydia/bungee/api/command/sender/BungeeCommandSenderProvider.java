package com.nolydia.bungee.api.command.sender;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.nolydia.common.api.command.sender.CommandSenderProvider;
import com.nolydia.common.api.command.sender.exceptions.UnsupportedSenderException;
import com.nolydia.common.api.console.Console;
import com.nolydia.common.api.player.repository.PlayerRepository;
import net.md_5.bungee.api.CommandSender;
import net.md_5.bungee.api.connection.ProxiedPlayer;

// TODO: 06/03/2021 Éventuellement ajouter une autre classe (faire les vérifs dans celle-ci et getPlayerSender() et getConsoleSender() dans une autre)
public class BungeeCommandSenderProvider implements CommandSenderProvider<CommandSender> {

    private final PlayerRepository playerRepository;
    private final Console console;
    private final CommandSender bungeeConsole;

    @Inject
    public BungeeCommandSenderProvider(PlayerRepository playerRepository, Console console, @Named("BungeeConsole") CommandSender bungeeConsole) {
        this.playerRepository = playerRepository;
        this.console = console;
        this.bungeeConsole = bungeeConsole;
    }

    public com.nolydia.common.api.command.sender.CommandSender get(CommandSender sender) {
        if (sender instanceof ProxiedPlayer) {
            return playerRepository.getPlayer(((ProxiedPlayer) sender).getUniqueId());
        } else if (sender == bungeeConsole) {
            return console;
        } else {
            throw new UnsupportedSenderException();
        }
    }
}
