package com.nolydia.common.api.plugin;

import com.google.inject.Module;

import java.util.Collection;
import java.util.Collections;

public interface ModularPlugin {

    default Collection<Module> getModules() {
        return Collections.emptyList();
    }
}
