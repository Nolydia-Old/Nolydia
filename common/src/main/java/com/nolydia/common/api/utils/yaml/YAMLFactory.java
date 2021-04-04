package com.nolydia.common.api.utils.yaml;

import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

public interface YAMLFactory {

    Yaml create(Constructor constructor);

    Yaml create();
}
