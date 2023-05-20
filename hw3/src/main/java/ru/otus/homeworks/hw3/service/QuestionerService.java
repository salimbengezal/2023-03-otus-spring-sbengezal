package ru.otus.homeworks.hw3.service;

import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.domain.Answer;

import java.util.List;

public interface QuestionerService {

    List<Answer> getAnswersByQuestions(List<Question> questions);

}
