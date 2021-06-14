package com.nolydia.common.api.plugin.shutdown;

import com.google.inject.Inject;
import com.google.inject.Singleton;

import java.util.ArrayList;
import java.util.List;

@Singleton
public class PluginCloser {

    private final List<CloseableResource> hooks;

    @Inject
    public PluginCloser() {
        this.hooks = new ArrayList<>();
    }

    public void addHook(CloseableResource hook) {
        hooks.add(hook);
    }

    public void close() throws Exception {
        for (CloseableResource hook : hooks) {
            hook.close();
        }
    }
}
