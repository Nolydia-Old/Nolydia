package com.nolydia.common.api.listener.inject;

import com.google.inject.Inject;
import com.nolydia.common.api.listener.ListenerRegisterer;

import java.util.Set;

public class ListenerPostExecutorImpl<T> implements ListenerPostExecutor {

    private final Set<T> listeners;
    private final ListenerRegisterer<T> registerer;

    @Inject
    public ListenerPostExecutorImpl(Set<T> listeners, ListenerRegisterer<T> registerer) {
        this.listeners = listeners;
        this.registerer = registerer;
    }

    @Override
    public void registerListeners() {
        listeners.forEach(registerer::registerListener);
    }
}
