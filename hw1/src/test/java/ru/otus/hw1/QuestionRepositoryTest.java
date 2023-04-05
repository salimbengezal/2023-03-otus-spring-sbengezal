package ru.otus.hw1;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.otus.hw1.exceptions.NotEnoughElementsException;
import ru.otus.hw1.repository.QuestionRepository;
import ru.otus.hw1.repository.impl.CsvQuestionRepository;

public class QuestionRepositoryTest {

    @Test
    void shouldThrowNotEnoughElementsException() {
        QuestionRepository repository = new CsvQuestionRepository("wrong-questions.csv", ";");
         Assertions.assertThrows(NotEnoughElementsException.class, repository::getAll);
    }

}
