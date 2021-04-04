package com.nolydia.common.core.command.requirements;

import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.command.requirement.Condition;
import com.nolydia.common.api.command.requirement.Requirement;
import com.nolydia.common.api.internalization.InternalizationMessage;
import com.nolydia.common.api.player.Player;

import java.util.Collections;
import java.util.List;

public class SenderIsPlayerRequirement implements Requirement {

    @Override
    public List<Condition<CommandSender>> getConditions() {
        return Collections.singletonList(new Condition<>() {
            @Override
            public boolean check(CommandSender o) {
                return o instanceof Player;
            }

            @Override
            public InternalizationMessage getErrorMessage(CommandSender o) {
                return new InternalizationMessage("requirement.sender_is_player");
            }
        });
    }
}
