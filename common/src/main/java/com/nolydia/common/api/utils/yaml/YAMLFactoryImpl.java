package com.nolydia.common.api.utils.yaml;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public class YAMLFactoryImpl implements YAMLFactory {

    @Override
    public Yaml create(Constructor constructor) {
        return new Yaml(constructor);
    }

    @Override
    public Yaml create() {
        return new Yaml();
    }
}
