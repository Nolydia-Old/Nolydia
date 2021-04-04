package com.nolydia.common.api.command.sender;

import com.nolydia.common.api.internalization.InternalizationEntity;
import com.nolydia.common.api.internalization.InternalizationMessage;
import com.nolydia.common.api.message.MessageReceiver;
import com.nolydia.common.api.permission.PermissionEntity;

public interface CommandSender extends InternalizationEntity, MessageReceiver, PermissionEntity {

    void sendMessage(InternalizationMessage message);
}
