package ru.otus.homeworks.hw3.config;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

import java.util.Locale;
import java.util.Map;

@ConfigurationProperties("application")
@Getter
@Setter
public class AppProperties {

    private Locale locale;

    private Double passingScore;

    private Map<Locale,Map<String,String>> questionsFile;

}
