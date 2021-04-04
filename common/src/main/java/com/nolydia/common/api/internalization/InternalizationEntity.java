package com.nolydia.common.api.internalization;

/**
 * Represents an entity that has a locale
 */
public interface InternalizationEntity {

    Locale getLocale();

    void setLocale(Locale locale);
}
