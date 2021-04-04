package com.nolydia.common.api.player.repository;

import com.nolydia.common.api.player.Player;

import java.util.Optional;
import java.util.UUID;

public interface PlayerRepository {

    Player getPlayer(UUID uuid);

    Optional<Player> getPlayer(String name);
}
