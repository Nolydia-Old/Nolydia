package com.nolydia.common.api.command;

import com.google.inject.AbstractModule;
import com.google.inject.assistedinject.FactoryModuleBuilder;
import com.google.inject.multibindings.Multibinder;
import com.nolydia.common.api.command.inject.CommandPostExecutor;
import com.nolydia.common.api.command.inject.CommandPostExecutorImpl;
import com.nolydia.common.api.command.repository.CommandRepository;
import com.nolydia.common.api.command.repository.CommandRepositoryImpl;

public class CommandModule extends AbstractModule {

    @Override
    protected void configure() {
        install(new FactoryModuleBuilder()
                .build(CommandFactory.class)
        );

        Multibinder.newSetBinder(binder(), Command.class);

        bind(CommandRepository.class).to(CommandRepositoryImpl.class);

        bind(CommandPostExecutor.class).to(CommandPostExecutorImpl.class);
    }
}
