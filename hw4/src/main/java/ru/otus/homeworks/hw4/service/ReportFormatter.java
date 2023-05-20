package ru.otus.homeworks.hw4.service;

import ru.otus.homeworks.hw4.domain.Answer;
import ru.otus.homeworks.hw4.domain.UserProfile;

import java.util.List;

public interface ReportFormatter {

    String formatMessage(UserProfile profile, List<Answer> answers, double passingScore);

}
