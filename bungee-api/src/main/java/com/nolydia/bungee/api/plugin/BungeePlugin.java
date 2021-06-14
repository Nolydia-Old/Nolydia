package com.nolydia.bungee.api.plugin;

import com.google.inject.Injector;
import com.nolydia.bungee.api.plugin.bootstrap.BungeePluginBootstrapper;
import com.nolydia.common.api.plugin.ModularPlugin;
import com.nolydia.common.api.plugin.shutdown.PluginCloser;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;

public class BungeePlugin extends Plugin implements ModularPlugin {

    protected Injector injector;

    @Override
    public void onEnable() {
        try {
            BungeePluginBootstrapper bootstrapper = new BungeePluginBootstrapper(this);
            injector = bootstrapper.bootstrap();
        } catch (Exception e) {
            e.printStackTrace();

            ProxyServer.getInstance().stop();
        }
    }

    @Override
    public void onDisable() {
        try {
            injector.getInstance(PluginCloser.class).close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
