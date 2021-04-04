package com.nolydia.common.core.command.arguments;

import com.google.inject.Inject;
import com.nolydia.common.api.command.argument.Argument;
import com.nolydia.common.api.command.argument.exceptions.ArgumentException;
import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.internalization.InternalizationMessage;
import com.nolydia.common.api.player.Player;
import com.nolydia.common.api.player.repository.PlayerRepository;

import java.util.Optional;

public class PlayerArgument implements Argument<Player> {

    private static final String ARGUMENT_BASE_PATH = "argument.default.player.";

    private final PlayerRepository playerRepository;

    @Inject
    public PlayerArgument(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    @Override
    public InternalizationMessage getName() {
        return new InternalizationMessage(ARGUMENT_BASE_PATH + "name");
    }

    @Override
    public Player get(CommandSender sender, String name) {
        Optional<Player> optionalPlayer = playerRepository.getPlayer(name);

        if (optionalPlayer.isPresent()) {
            return optionalPlayer.get();
        } else {
            throw new ArgumentException(new InternalizationMessage(ARGUMENT_BASE_PATH + "no_matching_player"));
        }
    }
}
