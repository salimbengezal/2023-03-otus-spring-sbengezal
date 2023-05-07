package ru.otus.homeworks.hw3.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class Messages {

    private final MessageSource messageSource;

    private final AppProperties props;

    public String getLocalized(String parameter) {
        return messageSource.getMessage(parameter, new String[]{}, props.getLocale());
    }
}
