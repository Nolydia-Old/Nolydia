package com.nolydia.common.api.plugin.bootstrap;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.Module;
import com.nolydia.common.api.command.inject.CommandPostExecutor;
import com.nolydia.common.api.listener.inject.ListenerPostExecutor;
import com.nolydia.common.api.persistence.sql.access.SQLAccess;
import com.nolydia.common.api.plugin.ModularPlugin;
import com.nolydia.common.api.plugin.shutdown.PluginCloser;

import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PluginBootstrapper {

    public Injector boostrap(Module bootstrapModule, ModularPlugin plugin) throws Exception {
        Injector injector = Guice.createInjector(
                Stream.concat(
                        Stream.of(
                                bootstrapModule,
                                new CommonModule()
                        ),
                        plugin.getModules().stream()
                ).collect(Collectors.toList())
        );

        injector.getInstance(CommandPostExecutor.class).registerCommands();
        injector.getInstance(ListenerPostExecutor.class).registerListeners();

        PluginCloser pluginCloser = injector.getInstance(PluginCloser.class);

        SQLAccess sqlAccess = injector.getInstance(SQLAccess.class);
        //sqlAccess.initPool();

        pluginCloser.addHook(sqlAccess);

        return injector;
    }
}
