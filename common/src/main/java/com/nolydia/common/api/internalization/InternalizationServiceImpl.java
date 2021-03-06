package com.nolydia.common.api.internalization;

import com.google.inject.Inject;
import com.nolydia.common.api.internalization.exceptions.UnsupportedLocaleException;
import com.nolydia.common.api.internalization.providers.TranslationMapProvider;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.Map;

/**
 * This class is the default {@link InternalizationService} implementation.
 */
public class InternalizationServiceImpl implements InternalizationService {

    private final Map<Locale, Map<String, String>> translations;

    @Inject
    public InternalizationServiceImpl(TranslationMapProvider translationMapProvider) {
        Map<Locale, Map<String, String>> translations;

        try {
            translations = translationMapProvider.get();
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();

            translations = UnavailableTranslationMap.getDefaultTranslationMap();
        }

        this.translations = translations;
    }

    @Override
    public String getMessage(InternalizationEntity entity, InternalizationMessage message) {
        return getMessage(entity.getLocale(), message);
    }

    @Override
    public String getMessage(Locale locale, InternalizationMessage internalizationMessage) {
        if (!translations.containsKey(locale)) {
            throw new UnsupportedLocaleException(locale.getTag());
        }

        Map<String, String> translations = this.translations.get(locale);

        String path = internalizationMessage.getPath();

        if (!translations.containsKey(path)) {
            throw new IllegalArgumentException(String.format("No message with path %s has been found", path));
        }

        String message = translations.get(path);

        for (InternalizationArgument argument : internalizationMessage.getArguments()) {
            String argumentName = argument.getName();

            String replacement = message.replace('%' + argumentName + '%', argument.getValue().toString());

            if (replacement.equals(message)) {
                throw new IllegalArgumentException(String.format("No argument with name %s has been found", argumentName));
            }

            message = replacement;
        }

        return message;
    }
}
