package ru.otus.homeworks.hw3;

import org.junit.jupiter.api.*;
import ru.otus.homeworks.hw3.config.AppProperties;
import ru.otus.homeworks.hw3.exceptions.QuestionReadingException;
import ru.otus.homeworks.hw3.repository.QuestionRepository;
import ru.otus.homeworks.hw3.repository.impl.CsvQuestionRepository;

@DisplayName("Репозиторий с вопросами ")
public class QuestionRepositoryTest {

    private QuestionRepository repository;

    private AppProperties properties;

    @BeforeEach
    public void setUp() {
        repository = new CsvQuestionRepository(properties);
    }

    @DisplayName("вызывает исключение")
    // TODO как считать тестовый application.yml для получения wrong-questions.csv
    @Test
    @Disabled
    void shouldThrowNotEnoughElementsException() {
        Assertions.assertThrows(QuestionReadingException.class, repository::getAll);
    }

}
