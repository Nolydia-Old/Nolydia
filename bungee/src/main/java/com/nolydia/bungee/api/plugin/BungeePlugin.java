package com.nolydia.bungee.api.plugin;

import com.google.inject.Injector;
import com.nolydia.bungee.api.plugin.bootstrap.BungeePluginBootstrapper;
import com.nolydia.common.api.plugin.ModularPlugin;
import com.nolydia.common.api.plugin.closing.PluginCloser;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeePlugin extends Plugin implements ModularPlugin {

    protected Injector injector;

    @Override
    public void onEnable() {
        BungeePluginBootstrapper bootstrapper = new BungeePluginBootstrapper(this);
        injector = bootstrapper.bootstrap();

        injector.getInstance(PluginCloser.class).close();
    }
}
