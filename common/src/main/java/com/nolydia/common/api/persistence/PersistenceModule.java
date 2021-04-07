package com.nolydia.common.api.persistence;

import com.google.inject.AbstractModule;
import com.nolydia.common.api.persistence.sql.SQLModule;

public class PersistenceModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new SQLModule());
    }
}
