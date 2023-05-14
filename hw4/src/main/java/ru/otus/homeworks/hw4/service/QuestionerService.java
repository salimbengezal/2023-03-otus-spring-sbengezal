package ru.otus.homeworks.hw4.service;

import ru.otus.homeworks.hw4.domain.Answer;
import ru.otus.homeworks.hw4.domain.Question;

import java.util.List;

public interface QuestionerService {

    List<Answer> getAnswersByQuestions(List<Question> questions);

}
