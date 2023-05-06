package ru.otus.homeworks.hw3.service.impl;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.domain.UserProfile;
import ru.otus.homeworks.hw3.repository.QuestionRepository;
import ru.otus.homeworks.hw3.service.*;

import java.util.List;

@Service
public class SimpleKnowledgeCheckerService implements KnowledgeCheckerService {

    private final double passingScore;

    private final QuestionRepository questionRepository;

    private final IOService ioService;

    private final QuestionerService questionerService;

    private final QAFormatter qaFormatter;

    private final ReportFormatter reportFormatter;

    public SimpleKnowledgeCheckerService(@Value("${passing-score}") double passingScore,
                                         QuestionRepository questionRepository,
                                         IOService ioService,
                                         QuestionerService questionerService,
                                         QAFormatter qaFormatter,
                                         ReportFormatter reportFormatter) {
        this.passingScore = passingScore;
        this.questionRepository = questionRepository;
        this.ioService = ioService;
        this.questionerService = questionerService;
        this.qaFormatter = qaFormatter;
        this.reportFormatter = reportFormatter;
    }

    @Override
    public void run() {
        UserProfile profile = getUserProfile();
        List<Question> questions = questionRepository.getAll();
        List<Answer> answers = getAnswersByQuestions(questions);
        showReport(profile, answers, passingScore);
    }

    private void showReport(UserProfile profile, List<Answer> answers, double passingScore) {
        String reportText = reportFormatter.formatMessage(profile, answers, passingScore);
        ioService.showMessageInline(reportText);
    }

    private UserProfile getUserProfile() {
        ioService.showMessage("%s:".formatted("Name"));
        String name = ioService.readString();
        ioService.showMessage("%s:".formatted("Surname"));
        String surname = ioService.readString();
        return new UserProfile(name, surname);
    }

    private List<Answer> getAnswersByQuestions(List<Question> questions) {
        ioService.showMessageInline("%s:".formatted("Please answer the following questions"));
        return questions.stream().map(question -> {
            String questionText = qaFormatter.formatQuestion(question);
            ioService.showMessageInline(questionText);
            return questionerService.getAnswerByQuestion(question);
        }).toList();
    }

}
