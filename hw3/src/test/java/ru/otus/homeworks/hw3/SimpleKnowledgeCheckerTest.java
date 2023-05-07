package ru.otus.homeworks.hw3;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.homeworks.hw3.repository.QuestionRepository;
import ru.otus.homeworks.hw3.service.KnowledgeCheckerService;
import ru.otus.homeworks.hw3.service.ProfileService;
import ru.otus.homeworks.hw3.service.QuestionerService;
import ru.otus.homeworks.hw3.service.ReporterService;
import ru.otus.homeworks.hw3.service.impl.SimpleKnowledgeCheckerService;

import static org.mockito.Mockito.*;

@DisplayName("Сервис тестирования должен ")
@ExtendWith(MockitoExtension.class)
public class SimpleKnowledgeCheckerTest {

    @Mock
    private QuestionRepository repository;

    @Mock
    private QuestionerService questionerService;

    @Mock
    private ReporterService reporterService;

    @Mock
    private ProfileService profileService;

    private KnowledgeCheckerService checkerService;

    @BeforeEach
    public void setUp() {
        checkerService = new SimpleKnowledgeCheckerService(
                repository,
                questionerService,
                reporterService,
                profileService);
    }

    @DisplayName("вызывает хотя бы раз получение информации о пользователе")
    @Test
    void shouldCallGetProfile() {
        checkerService.run();
        verify(profileService, times(1)).getProfile();
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
        verify(reporterService, times(1)).showReport(any(), any());
    }

}
