package ru.otus.hw1;

import org.junit.jupiter.api.Test;
import ru.otus.hw1.repository.QuestionRepository;
import ru.otus.hw1.repository.QuestionRepositoryCsv;

public class QuestionRepositoryTest {

    @Test
    void shouldHaveAtLeastTwoOptions() {
        QuestionRepository repository = new QuestionRepositoryCsv("wrong-questions.csv", ";");
        assert (repository.getAll().size() == 1);
    }

}
