package com.nolydia.common.api.player;

import com.google.inject.AbstractModule;
import com.nolydia.common.api.player.repository.PlayerRepository;
import com.nolydia.common.api.player.repository.PluginPlayerRepository;

public class PlayerModule extends AbstractModule {

    @Override
    protected void configure() {
        bind(PlayerRepository.class).to(PluginPlayerRepository.class);
    }
}
