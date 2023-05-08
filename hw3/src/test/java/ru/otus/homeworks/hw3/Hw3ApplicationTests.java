package ru.otus.homeworks.hw3;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homeworks.hw3.runners.QuizRunner;

@SpringBootTest
class Hw3ApplicationTests {

    @MockBean
    QuizRunner quizRunner;

    @Test
    void contextLoads() {
    }

}
