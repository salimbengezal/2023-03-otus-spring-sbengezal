package ru.otus.hw1.service.impl;

import ru.otus.hw1.domain.Answer;
import ru.otus.hw1.domain.Question;
import ru.otus.hw1.repository.QuestionRepository;
import ru.otus.hw1.service.IOStreamService;
import ru.otus.hw1.service.KnowledgeCheckerService;
import ru.otus.hw1.service.MessageFormatterService;
import ru.otus.hw1.service.QuestionerService;

import java.util.ArrayList;
import java.util.List;

public class SimpleKnowledgeCheckerService implements KnowledgeCheckerService {

    private final QuestionRepository questionRepository;

    private final IOStreamService ioService;

    private final QuestionerService questionerService;

    private final MessageFormatterService formatterService;

    public SimpleKnowledgeCheckerService(QuestionRepository repository,
                                         IOStreamService ioService,
                                         QuestionerService questionerService,
                                         MessageFormatterService formatterService) {
        this.questionRepository = repository;
        this.ioService = ioService;
        this.questionerService = questionerService;
        this.formatterService = formatterService;
    }

    @Override
    public void run() {
        List<Question> questions = questionRepository.getAll();
        List<Answer> answers = new ArrayList<>();
        ioService.showMessage("Please answer the following questions:");
        questions.forEach(question -> {
            String questionText = formatterService.formatQuestion(question);
            ioService.showMessage(questionText);
            Answer answer = questionerService.getAnswerByQuestion(question);
            answers.add(answer);
        });
        String resultsText = formatterService.formatAnswers(answers);
        ioService.showMessage(resultsText);
    }

}
