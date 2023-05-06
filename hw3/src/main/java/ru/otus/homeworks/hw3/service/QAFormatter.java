package ru.otus.homeworks.hw3.service;

import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.Question;

public interface QAFormatter {

    String formatQuestion(Question question);

    String formatAnswer(Answer answer);

}
