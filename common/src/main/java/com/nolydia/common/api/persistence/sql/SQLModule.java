package com.nolydia.common.api.persistence.sql;

import com.google.inject.AbstractModule;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import com.nolydia.common.api.configuration.ObjectMapper;
import com.nolydia.common.api.configuration.ObjectMapperFactory;
import com.nolydia.common.api.persistence.sql.access.SQLAccess;
import com.nolydia.common.api.persistence.sql.access.SQLAccessImpl;
import com.nolydia.common.api.persistence.sql.configuration.SQLConfiguration;
import com.nolydia.common.api.persistence.sql.configuration.SQLConfigurationProvider;

import java.io.IOException;
import java.io.InputStream;

public class SQLModule extends AbstractModule {

    @Override
    protected void configure() {
        install(ThrowingProviderBinder.forModule(this));

        bind(SQLAccess.class).to(SQLAccessImpl.class);
    }

    @CheckedProvides(SQLConfigurationProvider.class)
    public SQLConfiguration provideSQLConfiguration(ObjectMapperFactory mapperFactory) throws IOException {
        InputStream inputStream = SQLModule.class.getClassLoader().getResourceAsStream("sql.yml");

        if (inputStream == null) {
            throw new IOException("The SQL configuration file could not be found");
        }

        ObjectMapper mapper = mapperFactory.createObjectMapper(inputStream);

        return mapper.get(SQLConfiguration.class);
    }
}
