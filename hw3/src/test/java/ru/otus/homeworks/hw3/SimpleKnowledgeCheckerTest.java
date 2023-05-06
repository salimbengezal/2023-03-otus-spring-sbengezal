package ru.otus.homeworks.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homeworks.hw3.repository.QuestionRepository;
import ru.otus.homeworks.hw3.service.*;
import ru.otus.homeworks.hw3.service.impl.SimpleKnowledgeCheckerService;

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
    private QAFormatter formatter;

    @Mock
    private ReportFormatter reportFormatter;

    private KnowledgeCheckerService checkerService;

    @BeforeEach
    public void setUp() {
        double passingScore = 0.6;
        checkerService = new SimpleKnowledgeCheckerService(
                passingScore,
                repository,
                ioService,
                questionerService,
                formatter,
                reportFormatter
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
        verify(reportFormatter, times(1)).formatMessage(any(), any(), anyDouble());
    }

}
