package com.nolydia.common.api.internalization;

public class InternalizationArgument {

    private final String name;
    private final Object value;

    public InternalizationArgument(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public Object getValue() {
        return value;
    }
}
