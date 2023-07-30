package ru.otus.homeworks.hw10;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
@DisplayName("Приложение должно ")
public class Hw10ApplicationTest {

    @Test
    @DisplayName("загружать контекст")
    void contextLoads() {

    }
}
