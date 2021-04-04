package com.nolydia.common.api.configuration;

import java.util.Map;

public interface Configuration {

    String getName();

    String get(String path);

    Map<String, String> getContent();
}
