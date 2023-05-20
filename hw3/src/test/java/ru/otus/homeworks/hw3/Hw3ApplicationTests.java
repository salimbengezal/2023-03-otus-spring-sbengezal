package ru.otus.homeworks.hw3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;
import ru.otus.homeworks.hw3.runners.QuizRunner;

@SpringBootTest
class Hw3ApplicationTests {

    @ComponentScan(basePackages = "ru.otus.homeworks.hw3.*",
            excludeFilters = @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = QuizRunner.class))
    @Configuration
    static class NestedConfiguration {
    }

    @Test
    void contextLoads() {
    }

}
