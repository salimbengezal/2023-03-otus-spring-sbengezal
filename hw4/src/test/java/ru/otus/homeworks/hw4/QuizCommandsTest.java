package ru.otus.homeworks.hw4;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.shell.CommandNotCurrentlyAvailable;
import org.springframework.shell.InputProvider;
import org.springframework.shell.Shell;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Тест с shell командами должен ")
public class QuizCommandsTest {

    private InputProvider inputProvider;

    @Autowired
    private Shell shell;

    @BeforeEach
    void setUp() {
        inputProvider = mock(InputProvider.class);
    }

    @Test
    @DisplayName(" запрещать вывод результатов если пользователь не залогинен")
    void shouldReturnCommandNotCurrentlyAvailableWhenUserNotTestedYet() {
        when(inputProvider.readInput())
                .thenReturn(() -> "results")
                .thenReturn(null);

        assertThatCode(() -> shell.run(inputProvider)).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

    @Test
    @DisplayName(" прерывать запуск если пользователь не залогинен")
    void shouldReturnCommandNotCurrentlyAvailableWhenUserNotLoggedIn() {
        when(inputProvider.readInput())
                .thenReturn(() -> "start")
                .thenReturn(null);

        assertThatCode(() -> shell.run(inputProvider)).isInstanceOf(CommandNotCurrentlyAvailable.class);
    }

}
