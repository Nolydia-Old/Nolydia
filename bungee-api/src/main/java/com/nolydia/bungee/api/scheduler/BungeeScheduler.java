package com.nolydia.bungee.api.scheduler;

import com.google.inject.Inject;
import com.nolydia.common.api.scheduler.Scheduler;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.api.scheduler.TaskScheduler;

import java.util.concurrent.TimeUnit;

public class BungeeScheduler implements Scheduler {

    private final Plugin plugin;
    private final TaskScheduler scheduler;

    @Inject
    public BungeeScheduler(Plugin plugin, TaskScheduler scheduler) {
        this.plugin = plugin;
        this.scheduler = scheduler;
    }

    @Override
    public void runTaskAsynchronously(Runnable runnable) {
        scheduler.runAsync(plugin, runnable);
    }

    @Override
    public void runTaskLater(Runnable runnable, long delay, TimeUnit timeUnit) {
        scheduler.schedule(plugin, runnable, delay, timeUnit);
    }

    @Override
    public void runRepeatingTask(Runnable runnable, long delay, long period, TimeUnit timeUnit) {
        scheduler.schedule(plugin, runnable, delay, period, timeUnit);
    }
}
