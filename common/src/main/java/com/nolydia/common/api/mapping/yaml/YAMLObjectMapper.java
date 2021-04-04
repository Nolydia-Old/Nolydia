package com.nolydia.common.api.mapping.yaml;

import com.google.inject.Inject;
import com.google.inject.assistedinject.Assisted;
import com.nolydia.common.api.mapping.ObjectMapper;
import com.nolydia.common.api.mapping.exceptions.MapperException;
import com.nolydia.common.api.utils.yaml.YAMLFactory;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;

public class YAMLObjectMapper<T> implements ObjectMapper<T> {

    private final T data;

    @Inject
    public YAMLObjectMapper(YAMLFactory yamlFactory, @Assisted Path path, @Assisted Class<? extends T> targetClass) throws MapperException {
        try {
            Yaml yaml = yamlFactory.create(new Constructor(targetClass));
            InputStream in = Files.newInputStream(path);

            this.data = yaml.load(in);
        } catch (Exception e) {
            throw new MapperException(e);
        }
    }

    @Override
    public T get() {
        return data;
    }
}
