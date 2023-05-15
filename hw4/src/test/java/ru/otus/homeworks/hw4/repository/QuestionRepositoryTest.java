package ru.otus.homeworks.hw4.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homeworks.hw4.exceptions.QuestionReadingException;

@DisplayName("Репозиторий с вопросами ")
@SpringBootTest
public class QuestionRepositoryTest {

    @Autowired
    private QuestionRepository repository;

    @DisplayName("вызывает исключение, если недостаточно вариантов ответа")
    @Test
    void shouldThrowNotEnoughElementsException() {
        Assertions.assertThrows(QuestionReadingException.class, repository::getAll);
    }

}
