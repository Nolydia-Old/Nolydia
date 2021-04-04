package com.nolydia.common.api.plugin.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.nolydia.common.api.command.inject.CommandPostExecutor;
import com.nolydia.common.api.listener.inject.ListenerPostExecutor;
import com.nolydia.common.api.persistence.sql.access.SQLAccess;
import com.nolydia.common.api.plugin.ModularPlugin;

import java.util.stream.Collectors;
import java.util.stream.Stream;

// TODO: 26/02/2021 Remove try ... catch block
public class PluginBootstrapper {

    public Injector boostrap(Module bootstrapModule, Module coreModule, ModularPlugin plugin) {
        Injector injector = null;

        try {
            injector = Guice.createInjector(Stream
                    .concat(
                            Stream.of(
                                    bootstrapModule,
                                    new CommonModule(),
                                    coreModule),
                            plugin.getModules().stream())
                    .collect(Collectors.toList()));

            injector.getInstance(CommandPostExecutor.class).registerCommands();
            injector.getInstance(ListenerPostExecutor.class).registerListeners();

            injector.getInstance(SQLAccess.class).initPool();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return injector;
    }
}
