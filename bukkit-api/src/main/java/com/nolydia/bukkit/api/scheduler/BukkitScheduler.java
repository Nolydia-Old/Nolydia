package com.nolydia.bukkit.api.scheduler;

import com.google.inject.Inject;
import com.nolydia.common.api.scheduler.Scheduler;
import org.bukkit.plugin.Plugin;

import java.util.concurrent.TimeUnit;

public class BukkitScheduler implements Scheduler {

    private final Plugin plugin;
    private final org.bukkit.scheduler.BukkitScheduler scheduler;

    @Inject
    public BukkitScheduler(Plugin plugin, org.bukkit.scheduler.BukkitScheduler scheduler) {
        this.plugin = plugin;
        this.scheduler = scheduler;
    }

    @Override
    public void runTaskAsynchronously(Runnable runnable) {
        scheduler.runTaskAsynchronously(plugin, runnable);
    }

    @Override
    public void runTaskLater(Runnable runnable, long delay, TimeUnit timeUnit) {
        scheduler.runTaskLater(plugin, runnable, toTicks(delay, timeUnit));
    }

    @Override
    public void runRepeatingTask(Runnable runnable, long delay, long period, TimeUnit timeUnit) {
        scheduler.runTaskTimer(plugin, runnable, toTicks(delay, timeUnit), toTicks(period, timeUnit));
    }

    private long toTicks(long duration, TimeUnit unit) {
        return unit.toSeconds(duration) * 20;
    }
}
