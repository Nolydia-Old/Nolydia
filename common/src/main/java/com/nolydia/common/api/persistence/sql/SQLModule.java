package com.nolydia.common.api.persistence.sql;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import com.nolydia.common.api.mapping.ObjectMapper;
import com.nolydia.common.api.mapping.ObjectMapperFactory;
import com.nolydia.common.api.mapping.exceptions.MapperException;
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
    @Named("SQLConfigPath")
    public Path providesSQLConfigPath(@Named("ConfigurationPath") Path configurationPath) {
        return configurationPath.resolve(SQL_CONFIGURATION_FILE);
    }

    @Provides
    @Named("SQLCredentials")
    public SQLCredentials provideSQLCredentials(@Named("SQLConfigPath") Path configPath, ObjectMapperFactory mapperFactory) throws MapperException {
        ObjectMapper<SQLCredentials> mapper = mapperFactory.createObjectMapper(configPath, SQLCredentials.class);

        return mapper.get();
    }
}
