package ru.otus.hw1.service;

import ru.otus.hw1.domain.Answer;
import ru.otus.hw1.domain.Question;

import java.util.List;

public interface MessageFormatterService {

    String formatQuestion(Question question);

    String formatAnswers(List<Answer> answers);
}
