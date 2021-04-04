package com.nolydia.bukkit.api.command.sender;

import com.google.inject.Inject;
import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.command.sender.CommandSenderProvider;
import com.nolydia.common.api.command.sender.exceptions.UnsupportedSenderException;
import com.nolydia.common.api.console.Console;
import com.nolydia.common.api.player.repository.PlayerRepository;
import org.bukkit.command.ConsoleCommandSender;
import org.bukkit.entity.Player;

// TODO: 06/03/2021 Éventuellement ajouter une autre classe (faire les vérifs dans celle-ci et getPlayerSender() et getConsoleSender() dans une autre)
public class BukkitCommandSenderProvider implements CommandSenderProvider<org.bukkit.command.CommandSender> {

    private final PlayerRepository playerRepository;
    private final Console console;

    @Inject
    public BukkitCommandSenderProvider(PlayerRepository playerRepository, Console console) {
        this.playerRepository = playerRepository;
        this.console = console;
    }

    public CommandSender get(org.bukkit.command.CommandSender bukkitSender) {
        if (bukkitSender instanceof Player) {
            return playerRepository.getPlayer(((Player) bukkitSender).getUniqueId());
        } else if (bukkitSender instanceof ConsoleCommandSender) {
            return console;
        } else {
            throw new UnsupportedSenderException();
        }
    }
}
