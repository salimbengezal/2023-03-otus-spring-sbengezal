package ru.otus.homeworks.hw4.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homeworks.hw4.repository.QuestionRepository;

import static org.mockito.Mockito.*;

@DisplayName("Сервис тестирования должен ")
@SpringBootTest
public class KnowledgeCheckerTest {

    @MockBean
    private IOLocalizationService ioLocalizationService;

    @MockBean
    private QuestionRepository repository;

    @MockBean
    private ReporterService reporterService;

    @MockBean
    private UserProfileService profileService;

    @Autowired
    private KnowledgeCheckerService checkerService;

    @DisplayName("вызывать только метод тестирования")
    @Test
    void shouldCallRepositoryGetAll() {
        checkerService.run();
        verify(profileService, never()).getProfile();
        verify(reporterService, never()).showReport(any(), any());
        verify(repository, times(1)).getAll();
    }

}
