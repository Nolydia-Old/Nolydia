package com.nolydia.common.api.configuration;

import java.nio.file.Path;

public abstract class AbstractConfiguration implements Configuration {

    private final String name;

    public AbstractConfiguration(Path path) {
        String fileName = path.toFile().getName();

        int extensionPos = fileName.lastIndexOf(".");
        if (extensionPos == -1) {
            this.name = fileName;
        } else {
            this.name = fileName.substring(0, extensionPos);
        }
    }

    @Override
    public String getName() {
        return name;
    }
}
