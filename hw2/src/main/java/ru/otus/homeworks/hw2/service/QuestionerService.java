package ru.otus.homeworks.hw2.service;

import ru.otus.homeworks.hw2.domain.Question;
import ru.otus.homeworks.hw2.domain.Answer;

public interface QuestionerService {

    Answer getAnswerByQuestion(Question question);

}
