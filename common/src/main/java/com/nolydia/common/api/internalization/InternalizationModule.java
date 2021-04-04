package com.nolydia.common.api.internalization;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.name.Named;
import com.google.inject.throwingproviders.CheckedProvides;
import com.google.inject.throwingproviders.ThrowingProviderBinder;
import com.nolydia.common.api.configuration.Configuration;
import com.nolydia.common.api.configuration.ConfigurationFactory;
import com.nolydia.common.api.configuration.exceptions.ConfigurationException;
import com.nolydia.common.api.internalization.exceptions.UnsupportedLocaleException;
import com.nolydia.common.api.internalization.providers.InternalizationDirectoriesProvider;
import com.nolydia.common.api.internalization.providers.TranslationMapProvider;
import com.nolydia.common.api.io.directory.FileDirectory;
import com.nolydia.common.api.io.directory.FileDirectoryFactory;
import com.nolydia.common.api.io.directory.exceptions.FileDirectoryException;
import com.nolydia.common.api.plugin.annotations.DataFolder;

import java.nio.file.Path;
import java.util.*;

/**
 * Load all the plugin's translations, including those of the parent folders.
 * TODO Ajouter la gestion d'erreurs pour les traductions pas encore faites
 * TODO Ajouter le fait de mettre pouvoir plusieurs lignes dans les traductions
 */
public class InternalizationModule extends AbstractModule {

    @Override
    protected void configure() {
        install(ThrowingProviderBinder.forModule(this));

        bind(InternalizationService.class).to(InternalizationServiceImpl.class);
    }

    @CheckedProvides(InternalizationDirectoriesProvider.class)
    public List<FileDirectory> provideInternalizationDirectories(@Named("InternalizationPath") Path root, @DataFolder Path dataFolder, FileDirectoryFactory fileDirectoryFactory) throws FileDirectoryException {
        List<FileDirectory> directories = new ArrayList<>();

        Path currentFolder = root.resolve(dataFolder).toAbsolutePath();

        while (!currentFolder.equals(root.getParent())) {
            FileDirectory fileDirectory = fileDirectoryFactory.createFileDirectory(currentFolder);
            directories.add(fileDirectory);

            currentFolder = currentFolder.getParent();
        }

        return directories;
    }

    @CheckedProvides(TranslationMapProvider.class)
    public Map<Locale, Map<String, String>> provideTranslationMap(InternalizationDirectoriesProvider directories, ConfigurationFactory configurationFactory) throws FileDirectoryException, ConfigurationException {
        Map<Locale, Map<String, String>> translations = new HashMap<>();

        for (FileDirectory directory : directories.get()) {
            for (Path path : directory.listDirectory()) {
                Configuration configuration = configurationFactory.createConfiguration(path);

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
