package com.nolydia.common.api.player.repository;

import com.nolydia.common.api.player.Player;

import java.util.Optional;
import java.util.UUID;

public class PluginPlayerRepository implements PlayerRepository {

    @Override
    public Player getPlayer(UUID uuid) {
        return null;
    }

    @Override
    public Optional<Player> getPlayer(String name) {
        return Optional.empty();
    }
}
