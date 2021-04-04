package com.nolydia.common.api.player;

import com.nolydia.common.api.command.sender.CommandSender;
import com.nolydia.common.api.group.Group;

public interface Player extends CommandSender {

    Group getGroup();
}
