package ru.otus.homeworks.hw4.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import ru.otus.homeworks.hw4.repository.QuestionRepository;
import ru.otus.homeworks.hw4.service.KnowledgeCheckerService;
import ru.otus.homeworks.hw4.service.QuestionerService;
import ru.otus.homeworks.hw4.service.ReporterService;
import ru.otus.homeworks.hw4.service.UserProfileService;
import ru.otus.homeworks.hw4.service.impl.KnowledgeCheckerServiceImpl;

import static org.mockito.Mockito.*;

@DisplayName("Сервис тестирования должен ")
@SpringBootTest
public class KnowledgeCheckerTest {

    @Mock
    private QuestionRepository repository;

    @Mock
    private QuestionerService questionerService;

    @Mock
    private ReporterService reporterService;

    @Mock
    private UserProfileService profileService;

    private KnowledgeCheckerService checkerService;

    @BeforeEach
    public void setUp() {
        checkerService = new KnowledgeCheckerServiceImpl(repository, questionerService);
    }

    @DisplayName("вызывать только метод тестирования")
    @Test
    void shouldCallRepositoryGetAll() {
        checkerService.run();
        verify(profileService, never()).getProfile();
        verify(reporterService, never()).showReport(any(), any());
        verify(repository, times(1)).getAll();
    }

}
