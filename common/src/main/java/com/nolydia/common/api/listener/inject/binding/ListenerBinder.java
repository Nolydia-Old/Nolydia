package com.nolydia.common.api.listener.inject.binding;

import com.google.inject.Binder;
import com.google.inject.multibindings.Multibinder;

public class ListenerBinder<T> {

    private final Multibinder<T> binder;

    public ListenerBinder(Binder binder, Class<T> clazz) {
        this.binder = Multibinder.newSetBinder(binder, clazz);
    }

    public void addBinding(Class<? extends T> listener) {
        binder.addBinding().to(listener);
    }
}
