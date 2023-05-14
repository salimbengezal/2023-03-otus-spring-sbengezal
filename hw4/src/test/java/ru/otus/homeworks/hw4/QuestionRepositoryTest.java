package ru.otus.homeworks.hw4;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homeworks.hw4.config.QuestionFileProperties;
import ru.otus.homeworks.hw4.exceptions.QuestionReadingException;
import ru.otus.homeworks.hw4.repository.QuestionRepository;
import ru.otus.homeworks.hw4.repository.impl.CsvQuestionRepository;

import static org.mockito.Mockito.when;

@DisplayName("Репозиторий с вопросами ")
@SpringBootTest
public class QuestionRepositoryTest {

    private QuestionRepository repository;

    @Mock
    private QuestionFileProperties properties;

    @BeforeEach
    public void setUp() {
        when(properties.getFileName()).thenReturn("wrong-questions.csv");
        when(properties.getDelimiter()).thenReturn(";");
        repository = new CsvQuestionRepository(properties);
    }

    @DisplayName("вызывает исключение")
    @Test
    void shouldThrowNotEnoughElementsException() {
        Assertions.assertThrows(QuestionReadingException.class, repository::getAll);
    }

}
