package com.nolydia.common.api.persistence.sql;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import com.nolydia.common.api.configuration.ObjectMapper;
import com.nolydia.common.api.configuration.ObjectMapperFactory;
import com.nolydia.common.api.configuration.exceptions.MapperException;
import com.nolydia.common.api.persistence.sql.access.SQLAccess;
import com.nolydia.common.api.persistence.sql.access.SQLAccessImpl;

import java.nio.file.Path;

public class SQLModule extends AbstractModule {

    private static final String SQL_CONFIGURATION_FILE = "sql.yml";

    @Override
    protected void configure() {
        install(ThrowingProviderBinder.forModule(this));

        bind(SQLAccess.class).to(SQLAccessImpl.class);
    }

    @Provides
    @Named("SQLConfigurationPath")
    public Path providesSQLConfigPath(@Named("ConfigurationPath") Path configurationPath) {
        return configurationPath.resolve(SQL_CONFIGURATION_FILE);
    }

    @Provides
    @Named("SQLConfiguration")
    public SQLConfiguration provideSQLCredentials(@Named("SQLConfigurationPath") Path configPath, ObjectMapperFactory mapperFactory) throws MapperException {
        ObjectMapper mapper = mapperFactory.createObjectMapper(configPath);

        return mapper.get(SQLConfiguration.class);
    }
}
