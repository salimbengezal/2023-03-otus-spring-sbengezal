package ru.otus.homeworks.hw3.service;

import ru.otus.homeworks.hw3.domain.Question;
import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.UserProfile;

import java.util.List;

public interface MessageFormatterService {

    String formatQuestion(Question question);

    String formatAnswers(List<Answer> answers);

    String formatReport(UserProfile profile, List<Answer> answers, double passingScore);
}
