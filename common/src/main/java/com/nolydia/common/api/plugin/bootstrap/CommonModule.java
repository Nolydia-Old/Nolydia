package com.nolydia.common.api.plugin.bootstrap;

import com.google.inject.AbstractModule;
import com.nolydia.common.api.command.CommandModule;
import com.nolydia.common.api.configuration.ConfigurationModule;
import com.nolydia.common.api.internalization.InternalizationModule;
import com.nolydia.common.api.io.IOModule;
import com.nolydia.common.api.persistence.PersistenceModule;
import com.nolydia.common.api.player.PlayerModule;
import com.nolydia.common.api.server.ServerModule;

public class CommonModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new CommandModule());
        install(new ConfigurationModule());
        install(new InternalizationModule());
        install(new IOModule());
        install(new PersistenceModule());
        install(new PlayerModule());
        install(new ServerModule());
    }
}
