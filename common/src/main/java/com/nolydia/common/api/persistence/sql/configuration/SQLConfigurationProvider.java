package com.nolydia.common.api.persistence.sql.configuration;

import com.google.inject.throwingproviders.CheckedProvider;

import java.io.IOException;

public interface SQLConfigurationProvider extends CheckedProvider<SQLConfiguration> {

    @Override
    SQLConfiguration get() throws IOException;
}
