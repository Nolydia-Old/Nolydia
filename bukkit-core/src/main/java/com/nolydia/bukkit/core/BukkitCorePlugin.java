package com.nolydia.bukkit.core;

import com.google.inject.Module;
import com.nolydia.bukkit.api.plugin.BukkitPlugin;

import java.util.Collection;
import java.util.Collections;

public class BukkitCorePlugin extends BukkitPlugin {

    @Override
    public Collection<Module> getModules() {
        return Collections.singleton(new CoreModule());
    }
}
