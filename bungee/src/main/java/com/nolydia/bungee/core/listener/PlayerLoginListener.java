package com.nolydia.bungee.core.listener;

import com.google.inject.Inject;
import com.nolydia.common.api.plugin.annotations.Logging;
import net.md_5.bungee.api.event.LoginEvent;
import net.md_5.bungee.api.plugin.Listener;
import net.md_5.bungee.event.EventHandler;

import java.util.logging.Logger;

public class PlayerLoginListener implements Listener {

    private final Logger logger;

    @Inject
    public PlayerLoginListener(@Logging Logger logger) {
        this.logger = logger;
    }

    @EventHandler
    public void onPlayerLogin(LoginEvent event) {
        logger.info(String.format("%s connected to the proxy", event.getConnection().getName()));

        // TODO: 04/04/2021
        // 1) Charger données
        // 2) Lobby balancing
    }
}
