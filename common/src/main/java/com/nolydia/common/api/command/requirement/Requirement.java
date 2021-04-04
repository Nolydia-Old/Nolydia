package com.nolydia.common.api.command.requirement;

import com.nolydia.common.api.command.sender.CommandSender;

import java.util.List;

public interface Requirement {

    List<Condition<CommandSender>> getConditions();
}
