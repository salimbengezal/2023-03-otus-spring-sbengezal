package ru.otus.homeworks.hw4.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.otus.homeworks.hw4.domain.Answer;
import ru.otus.homeworks.hw4.domain.Question;
import ru.otus.homeworks.hw4.repository.QuestionRepository;
import ru.otus.homeworks.hw4.service.KnowledgeCheckerService;
import ru.otus.homeworks.hw4.service.QuestionerService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class KnowledgeCheckerServiceImpl implements KnowledgeCheckerService {

    private final QuestionRepository questionRepository;

    private final QuestionerService questionerService;

    @Override
    public List<Answer> run() {
        List<Question> questions = questionRepository.getAll();
        return questionerService.getAnswersByQuestions(questions);

    }


}
