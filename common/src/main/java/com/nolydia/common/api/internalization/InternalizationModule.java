package com.nolydia.common.api.internalization;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import com.nolydia.common.api.configuration.Configuration;
import com.nolydia.common.api.configuration.ConfigurationFactory;
import com.nolydia.common.api.internalization.exceptions.UnsupportedLocaleException;
import com.nolydia.common.api.internalization.providers.TranslationMapProvider;

import java.io.IOException;
import java.io.InputStream;
import java.net.URI;
import java.net.URISyntaxException;
import java.nio.file.FileSystem;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;
import java.util.stream.Collectors;

public class InternalizationModule extends AbstractModule {

    @Override
    protected void configure() {
        install(ThrowingProviderBinder.forModule(this));

        bind(InternalizationService.class).to(InternalizationServiceImpl.class).asEagerSingleton();
    }

    @CheckedProvides(TranslationMapProvider.class)
    public Map<Locale, Map<String, String>> provideTranslationMap(ConfigurationFactory configurationFactory) throws IOException, URISyntaxException {
        Map<Locale, Map<String, String>> translations = new HashMap<>();

        String jarPath = getClass().getProtectionDomain()
                .getCodeSource()
                .getLocation()
                .toURI()
                .getPath();

        URI uri = URI.create("jar:file:" + jarPath);

        try (FileSystem fileSystem = FileSystems.newFileSystem(uri, Collections.emptyMap())) {
            Path translationPath = fileSystem.getPath("translations");

            if (!Files.exists(translationPath)) {
                return Collections.emptyMap();
            }

            List<Path> paths = Files.walk(fileSystem.getPath("translations"))
                    .filter(Files::isRegularFile)
                    .collect(Collectors.toList());

            for (Path path : paths) {
                String fileName = path.getFileName().toString();
                InputStream inputStream = Files.newInputStream(path);

                Configuration configuration = configurationFactory.createConfiguration(fileName, inputStream);

                String localeTag = configuration.getName();
                Optional<Locale> optionalLocale = Locale.getByTag(localeTag);

                if (optionalLocale.isEmpty()) {
                    throw new UnsupportedLocaleException(localeTag);
                }

                Locale locale = optionalLocale.get();

                if (translations.containsKey(locale)) {
                    translations.get(locale).putAll(configuration.getContent());
                } else {
                    translations.put(locale, configuration.getContent());
                }
            }
        }

        return translations;
    }

    @Provides
    @Named("DefaultConsoleLocale")
    public Locale provideDefaultConsoleLocale() {
        return Locale.ENGLISH;
    }

    @Provides
    @Named("DefaultServerLocale")
    public Locale provideDefaultServerLocale() {
        return Locale.FRENCH;
    }
}
