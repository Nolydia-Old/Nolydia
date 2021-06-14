package com.nolydia.bukkit.api.plugin;

import com.google.inject.Injector;
import com.nolydia.bukkit.api.plugin.boostrap.BukkitPluginBootstrapper;
import com.nolydia.common.api.plugin.ModularPlugin;
import com.nolydia.common.api.plugin.shutdown.PluginCloser;
import org.bukkit.plugin.java.JavaPlugin;

public abstract class BukkitPlugin extends JavaPlugin implements ModularPlugin {

    protected Injector injector;

    @Override
    public void onEnable() {
        try {
            BukkitPluginBootstrapper bootstrapper = new BukkitPluginBootstrapper(this);
            injector = bootstrapper.bootstrap();
        } catch (Exception e) {
            e.printStackTrace();

            getServer().shutdown();
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
