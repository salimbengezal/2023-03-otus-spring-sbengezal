package ru.otus.homeworks.hw3;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import ru.otus.homeworks.hw3.runners.QuizRunner;

@SpringBootConfiguration
@ComponentScan(basePackages = "ru.otus.homeworks.hw3.*",
        excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = QuizRunner.class))
public class Hw3WithoutRunnerConfiguration {
}
