package com.nolydia.common.api.configuration.properties;

import com.google.common.collect.Maps;
import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nolydia.common.api.configuration.AbstractConfiguration;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class PropertiesConfiguration extends AbstractConfiguration {

    private final Map<String, String> content = new HashMap<>();

    @Inject
    public PropertiesConfiguration(@Assisted String fileName, @Assisted InputStream inputStream) throws IOException {
        super(fileName);

        try (inputStream) {
            Properties content = new Properties();
            content.load(inputStream);

            this.content.putAll(Maps.fromProperties(content));
        }
    }

    @Override
    public String get(String path) {
        return content.get(path);
    }

    @Override
    public Map<String, String> getContent() {
        return content;
    }
}
