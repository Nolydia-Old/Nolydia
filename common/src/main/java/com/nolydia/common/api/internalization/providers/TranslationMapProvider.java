package com.nolydia.common.api.internalization.providers;

import com.google.inject.throwingproviders.CheckedProvider;
import com.nolydia.common.api.internalization.Locale;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

public interface TranslationMapProvider extends CheckedProvider<Map<Locale, Map<String, String>>> {

    @Override
    Map<Locale, Map<String, String>> get() throws IOException, URISyntaxException;
}
