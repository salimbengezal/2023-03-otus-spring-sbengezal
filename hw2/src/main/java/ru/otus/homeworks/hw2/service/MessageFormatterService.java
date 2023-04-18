package ru.otus.homeworks.hw2.service;

import ru.otus.homeworks.hw2.domain.Question;
import ru.otus.homeworks.hw2.domain.Answer;
import ru.otus.homeworks.hw2.domain.UserProfile;

import java.util.List;

public interface MessageFormatterService {

    String formatQuestion(Question question);

    String formatAnswers(List<Answer> answers);

    String formatReport(UserProfile profile, List<Answer> answers, double passingScore);
}
