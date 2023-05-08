package ru.otus.homeworks.hw3.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties("application")
@Getter
@Setter
public class AppProperties implements QuestionFileProperties, QuizProperties {

    private Locale locale;

    private double passingScore;

    private Map<Locale, QuestionFile> localizedQuestionFiles;

    @Override
    public String getFileName() {
        return localizedQuestionFiles.get(locale).name();
    }

    @Override
    public String getDelimiter() {
        return localizedQuestionFiles.get(locale).delimiter();
    }
}
