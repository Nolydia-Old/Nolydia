package com.nolydia.bukkit.api.plugin;

import com.google.inject.Injector;
import com.nolydia.bukkit.api.plugin.boostrap.BukkitPluginBootstrapper;
import com.nolydia.common.api.plugin.ModularPlugin;
import com.nolydia.common.api.plugin.closing.PluginCloser;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BukkitPlugin extends JavaPlugin implements ModularPlugin {

    protected Injector injector;

    @Override
    public void onEnable() {
        BukkitPluginBootstrapper bootstrapper = new BukkitPluginBootstrapper(this);
        injector = bootstrapper.bootstrap();

        injector.getInstance(PluginCloser.class).close();
    }
}
