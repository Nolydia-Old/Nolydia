package com.nolydia.common.api.command.requirement;

import com.nolydia.common.api.internalization.InternalizationMessage;

public interface Condition<T> {

    default boolean abort(T o) {
        return !check(o);
    }

    boolean check(T o);

    InternalizationMessage getErrorMessage(T o);
}