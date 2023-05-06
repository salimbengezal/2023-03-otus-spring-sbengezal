package ru.otus.homeworks.hw2;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homeworks.hw2.repository.QuestionRepository;
import ru.otus.homeworks.hw2.service.IOService;
import ru.otus.homeworks.hw2.service.KnowledgeCheckerService;
import ru.otus.homeworks.hw2.service.MessageFormatterService;
import ru.otus.homeworks.hw2.service.QuestionerService;
import ru.otus.homeworks.hw2.service.impl.SimpleKnowledgeCheckerService;

import static org.mockito.Mockito.*;

@DisplayName("Сервис тестирования должен ")
@ExtendWith(MockitoExtension.class)
public class SimpleKnowledgeCheckerTest {

    @Mock
    private QuestionRepository repository;

    @Mock
    private IOService ioService;

    @Mock
    private QuestionerService questionerService;

    @Mock
    private MessageFormatterService formatter;

    private KnowledgeCheckerService checkerService;

    @BeforeEach
    public void setUp() {
        double passingScore = 0.6;
        checkerService = new SimpleKnowledgeCheckerService(
                passingScore,
                repository,
                ioService,
                questionerService,
                formatter
        );
    }

    @DisplayName("вызывает хотя бы раз чтение строки")
    @Test
    void shouldCallIOServiceReadString() {
       checkerService.run();
        verify(ioService, times(2)).readString();
    }

    @DisplayName("вызывает хотя бы раз чтение репозитория")
    @Test
    void shouldCallRepositoryGetAll() {
        checkerService.run();
        verify(repository, times(1)).getAll();
    }

    @DisplayName("вызывает хотя бы раз получение отчета")
    @Test
    void shouldCallFormatterReport() {
        checkerService.run();
        verify(formatter, times(1)).formatReport(any(), any(), anyDouble());
    }

}
