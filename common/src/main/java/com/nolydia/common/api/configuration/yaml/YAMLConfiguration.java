package com.nolydia.common.api.configuration.yaml;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nolydia.common.api.configuration.AbstractConfiguration;
import com.nolydia.common.api.configuration.exceptions.ConfigurationException;
import com.nolydia.common.api.utils.yaml.YAMLFactory;
import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashMap;
import java.util.Map;

public class YAMLConfiguration extends AbstractConfiguration {

    private final Map<String, String> content = new HashMap<>();

    @Inject
    public YAMLConfiguration(YAMLFactory yamlFactory, @Assisted Path path) throws ConfigurationException {
        super(path);

        Yaml yaml = yamlFactory.create();

        try (InputStream in = Files.newInputStream(path)) {
           Map<String, String> content = yaml.load(in);
           this.content.putAll(content);
        } catch (Exception e) {
            throw new ConfigurationException(e);
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
