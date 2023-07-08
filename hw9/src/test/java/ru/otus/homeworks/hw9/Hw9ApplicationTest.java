package ru.otus.homeworks.hw9;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
@DisplayName("Приложение должно ")
public class Hw9ApplicationTest {

    @Test
    @DisplayName("загружать контекст")
    void contextLoads() {

    }
}
