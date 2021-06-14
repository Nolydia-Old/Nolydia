package com.nolydia.common.api.configuration.yaml;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nolydia.common.api.configuration.AbstractConfiguration;
import org.yaml.snakeyaml.Yaml;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class YAMLConfiguration extends AbstractConfiguration {

    private final Map<String, String> content;

    @Inject
    public YAMLConfiguration(Yaml yaml, @Assisted String fileName, @Assisted InputStream inputStream) throws IOException {
        super(fileName);

        this.content = new HashMap<>();

        try (inputStream) {
            Map<String, String> content = yaml.load(inputStream);

            if (content == null) {
                content = Collections.emptyMap();
            }

            this.content.putAll(content);
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
