package ru.otus.homeworks.hw2.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw2.domain.Answer;
import ru.otus.homeworks.hw2.domain.Question;
import ru.otus.homeworks.hw2.domain.UserProfile;
import ru.otus.homeworks.hw2.repository.QuestionRepository;
import ru.otus.homeworks.hw2.service.IOService;
import ru.otus.homeworks.hw2.service.KnowledgeCheckerService;
import ru.otus.homeworks.hw2.service.MessageFormatterService;
import ru.otus.homeworks.hw2.service.QuestionerService;

import java.util.ArrayList;
import java.util.List;

@Service
public class SimpleKnowledgeCheckerService implements KnowledgeCheckerService {

    private final double passingScore;

    private final QuestionRepository questionRepository;

    private final IOService ioService;

    private final QuestionerService questionerService;

    private final MessageFormatterService formatterService;

    public SimpleKnowledgeCheckerService(@Value("${passing-score}") double passingScore,
                                         QuestionRepository questionRepository,
                                         IOService ioService,
                                         QuestionerService questionerService,
                                         MessageFormatterService formatterService) {
        this.passingScore = passingScore;
        this.questionRepository = questionRepository;
        this.ioService = ioService;
        this.questionerService = questionerService;
        this.formatterService = formatterService;
    }

    @Override
    public void run() {
        ioService.showMessage("Name:");
        String name = ioService.readString();
        ioService.showMessage("Surname:");
        String surname = ioService.readString();
        UserProfile profile = new UserProfile(name, surname);
        List<Question> questions = questionRepository.getAll();
        List<Answer> answers = new ArrayList<>();
        ioService.showMessageInline("Please answer the following questions:");
        questions.forEach(question -> {
            String questionText = formatterService.formatQuestion(question);
            ioService.showMessageInline(questionText);
            Answer answer = questionerService.getAnswerByQuestion(question);
            answers.add(answer);
        });
        String reportText = formatterService.formatReport(profile, answers, passingScore);
        ioService.showMessageInline(reportText);
    }

}
