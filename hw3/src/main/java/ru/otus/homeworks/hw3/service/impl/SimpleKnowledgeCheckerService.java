package ru.otus.homeworks.hw3.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.repository.QuestionRepository;
import ru.otus.homeworks.hw3.service.KnowledgeCheckerService;
import ru.otus.homeworks.hw3.service.UserProfileService;
import ru.otus.homeworks.hw3.service.QuestionerService;
import ru.otus.homeworks.hw3.service.ReporterService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimpleKnowledgeCheckerService implements KnowledgeCheckerService {

    private final QuestionRepository questionRepository;

    private final QuestionerService questionerService;

    private final ReporterService reporterService;

    private final UserProfileService profileService;


    @Override
    public void run() {
        UserProfile profile = profileService.getProfile();
        List<Question> questions = questionRepository.getAll();
        List<Answer> answers = questionerService.getAnswersByQuestions(questions);
        reporterService.showReport(profile, answers);
    }


}
