package com.nolydia.common.api.internalization;

import java.util.Optional;
import java.util.stream.Stream;

public enum Locale {

    ENGLISH("ENGLISH", "en", "EN"),
    FRENCH("FRENCH", "fr", "FR"),
    SPANISH("SPANISH", "es", "ES");

    private final String id;
    private final String language;
    private final String country;

    Locale(String name, String language, String country) {
        this.id = name;
        this.language = language;
        this.country = country;
    }

    public static Optional<Locale> getByTag(String tag) {
        return Stream.of(values())
                .filter(locale -> locale.getTag().equals(tag))
                .findAny();
    }

    public static Optional<Locale> getByName(String name) {
        return Stream.of(values())
                .filter(locale -> locale.getName().equals(name))
                .findAny();
    }

    public String getName() {
        return id;
    }

    public String getTag() {
        return language + "_" + country;
    }
}
