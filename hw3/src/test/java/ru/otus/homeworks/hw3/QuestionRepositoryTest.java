package ru.otus.homeworks.hw3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.homeworks.hw3.exceptions.QuestionReadingException;
import ru.otus.homeworks.hw3.repository.QuestionRepository;
import ru.otus.homeworks.hw3.repository.impl.CsvQuestionRepository;

@DisplayName("Репозиторий с вопросами ")
public class QuestionRepositoryTest {

    private QuestionRepository repository;

    @BeforeEach
    public void setUp() {
        repository = new CsvQuestionRepository("wrong-questions.csv", ";");
    }

    @DisplayName("вызывает исключение")
    @Test
    void shouldThrowNotEnoughElementsException() {
        Assertions.assertThrows(QuestionReadingException.class, repository::getAll);
    }

}
