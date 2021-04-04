package com.nolydia.common.api.internalization;

public interface InternalizationService {

    String getMessage(InternalizationEntity entity, InternalizationMessage message);

    String getMessage(Locale locale, InternalizationMessage message);
}
