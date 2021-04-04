package com.nolydia.bukkit.core.listener;

import com.google.inject.Inject;
import com.nolydia.common.api.internalization.InternalizationArgument;
import com.nolydia.common.api.internalization.InternalizationMessage;
import com.nolydia.common.api.internalization.InternalizationService;
import com.nolydia.common.api.internalization.Locale;
import com.nolydia.common.api.player.repository.PlayerRepository;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class PlayerJoinListener implements Listener {

    private final PlayerRepository playerRepository;
    private final InternalizationService internalizationService;

    @Inject
    public PlayerJoinListener(PlayerRepository playerRepository, InternalizationService internalizationService) {
        this.playerRepository = playerRepository;
        this.internalizationService = internalizationService;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event) {
        // internalizationService.getMessage(playerRepository.getPlayer(event.getPlayer().getUniqueId()), new InternalizationMessage("sayHello", event.getPlayer().getName()));
        event.getPlayer().sendMessage(internalizationService.getMessage(Locale.FRENCH, new InternalizationMessage("sayHello", new InternalizationArgument("player", event.getPlayer().getName()))));
    }
}
