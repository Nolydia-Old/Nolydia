package com.nolydia.common.api.internalization.providers;

import com.google.inject.throwingproviders.CheckedProvider;
import com.nolydia.common.api.configuration.exceptions.ConfigurationException;
import com.nolydia.common.api.internalization.Locale;
import com.nolydia.common.api.io.directory.exceptions.FileDirectoryException;

import java.util.Map;

public interface TranslationMapProvider extends CheckedProvider<Map<Locale, Map<String, String>>> {

    @Override
    Map<Locale, Map<String, String>> get() throws FileDirectoryException, ConfigurationException;
}
