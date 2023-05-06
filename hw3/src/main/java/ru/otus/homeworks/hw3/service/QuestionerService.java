package ru.otus.homeworks.hw3.service;

import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.domain.Answer;

public interface QuestionerService {

    Answer getAnswerByQuestion(Question question);

}
