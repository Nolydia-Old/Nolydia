package com.nolydia.common.api.plugin.bootstrap;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.nolydia.common.api.command.CommandModule;
import com.nolydia.common.api.configuration.ConfigurationModule;
import com.nolydia.common.api.internalization.InternalizationModule;
import com.nolydia.common.api.io.IOModule;
import com.nolydia.common.api.mapping.MappingModule;
import com.nolydia.common.api.persistence.PersistenceModule;
import com.nolydia.common.api.player.PlayerModule;
import com.nolydia.common.api.server.ServerModule;
import com.nolydia.common.api.utils.UtilsModule;

import java.nio.file.Path;

public class CommonModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new CommandModule());
        install(new ConfigurationModule());
        install(new InternalizationModule());
        install(new IOModule());
        install(new MappingModule());
        install(new PersistenceModule());
        install(new PlayerModule());
        install(new ServerModule());
        install(new UtilsModule());
    }

    @Provides
    @Named("ConfigurationPath")
    public Path provideConfigurationPath() {
        return Path.of("/users/juan/nolydia/configuration");
    }

    @Provides
    @Named("InternalizationPath")
    public Path provideInternalizationPath() {
        return Path.of("/users/juan/nolydia/internalization");
    }
}
