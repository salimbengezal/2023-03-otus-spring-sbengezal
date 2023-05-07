package ru.otus.homeworks.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homeworks.hw3.repository.QuestionFileProperties;
import ru.otus.homeworks.hw3.exceptions.QuestionReadingException;
import ru.otus.homeworks.hw3.repository.QuestionRepository;
import ru.otus.homeworks.hw3.repository.impl.CsvQuestionRepository;

@DisplayName("Репозиторий с вопросами ")
public class QuestionRepositoryTest {

    private QuestionRepository repository;

    @BeforeEach
    public void setUp() {
        QuestionFileProperties properties = new QuestionFileProperties() {
            @Override
            public String getFileName() {
                return "wrong-questions.csv";
            }

            @Override
            public String getDelimiter() {
                return ";";
            }
        };
        repository = new CsvQuestionRepository(properties);
    }

    @DisplayName("вызывает исключение")
    // TODO как считать тестовый application.yml для получения wrong-questions.csv
    @Test
    void shouldThrowNotEnoughElementsException() {
        Assertions.assertThrows(QuestionReadingException.class, repository::getAll);
    }

}
