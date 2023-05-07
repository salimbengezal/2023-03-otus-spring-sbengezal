package ru.otus.homeworks.hw3.repository.impl;

import org.springframework.stereotype.Component;
import ru.otus.homeworks.hw3.config.AppProperties;
import ru.otus.homeworks.hw3.repository.QuestionFileProperties;

import java.util.Map;

@Component
public class SimpleQuestionFileProperties implements QuestionFileProperties {

    private final Map<String, String> configMap;

    public SimpleQuestionFileProperties(AppProperties properties) {
        configMap = properties.getQuestionsFile().get(properties.getLocale());
    }

    @Override
    public String getFileName() {
        return configMap.get("name");
    }

    @Override
    public String getDelimiter() {
        return configMap.get("delimiter");
    }
}
