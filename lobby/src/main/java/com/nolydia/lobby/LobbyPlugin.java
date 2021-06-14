package com.nolydia.lobby;

import com.google.inject.Module;
import com.nolydia.bukkit.api.plugin.BukkitPlugin;

import java.util.Collection;
import java.util.Collections;

public class LobbyPlugin extends BukkitPlugin {

    @Override
    public Collection<Module> getModules() {
        return Collections.singleton(new LobbyModule());
    }
}
