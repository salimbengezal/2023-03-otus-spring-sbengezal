package ru.otus.homeworks.hw3.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw3.service.LocalizationService;

import java.util.Locale;

@Component
public class LocalizationServiceImpl implements LocalizationService {

    private final MessageSource messageSource;

    private final Locale locale;

    public LocalizationServiceImpl(MessageSource messageSource,
                                   @Value("${application.locale}") Locale locale) {
        this.messageSource = messageSource;
        this.locale = locale;
    }

    @Override
    public String getMessage(String key) {
        return getMessage(key, new String[]{});
    }

    @Override
    public String getMessage(String key, String... args) {
        return messageSource.getMessage(key, args, locale);
    }
}
