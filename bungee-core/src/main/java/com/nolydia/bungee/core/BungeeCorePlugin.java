package com.nolydia.bungee.core;

import com.google.inject.Module;
import com.nolydia.bungee.api.plugin.BungeePlugin;

import java.util.Collection;
import java.util.Collections;

public class BungeeCorePlugin extends BungeePlugin {

    @Override
    public Collection<Module> getModules() {
        return Collections.singleton(new CoreModule());
    }
}
