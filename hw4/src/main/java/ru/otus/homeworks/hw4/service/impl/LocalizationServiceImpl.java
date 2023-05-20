package ru.otus.homeworks.hw4.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw4.config.LocaleProperties;
import ru.otus.homeworks.hw4.service.LocalizationService;

@Component
@RequiredArgsConstructor
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;

    private final LocaleProperties localeProperties;

    @Override
    public String getMessage(String key) {
        return getMessage(key, new String[]{});
    }

    @Override
    public String getMessage(String key, String... args) {
        return messageSource.getMessage(key, args, localeProperties.getLocale());
    }
}
