package com.nolydia.common.api.scheduler;

import java.util.concurrent.TimeUnit;

public interface Scheduler {

    /**
     * Run a task asynchronously
     *
     * @param runnable task to run
     */
    void runTaskAsynchronously(Runnable runnable);

    /**
     * Run a task after a delay
     *
     * @param runnable task to run
     * @param delay    delay before executing the task
     * @param timeUnit time timeUnit to use
     */
    void runTaskLater(Runnable runnable, long delay, TimeUnit timeUnit);

    /**
     * Run a repeating task after a delay
     *
     * @param runnable task to run
     * @param delay    delay before executing the task
     * @param period   frequency with witch the task will be executed
     * @param timeUnit time timeUnit to use
     */
    void runRepeatingTask(Runnable runnable, long delay, long period, TimeUnit timeUnit);

    /**
     * Run a repeating task
     *
     * @param runnable task to run
     * @param period frequency with witch the task will be executed
     * @param timeUnit time timeUnit to use
     */
    default void runRepeatingTask(Runnable runnable, long period, TimeUnit timeUnit) {
        runRepeatingTask(runnable, period, 0, timeUnit);
    }
}
