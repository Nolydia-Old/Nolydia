package com.nolydia.common.api.listener;

public interface ListenerRegisterer<T> {

    void registerListener(T listener);
}
