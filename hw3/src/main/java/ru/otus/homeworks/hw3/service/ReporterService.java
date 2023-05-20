package ru.otus.homeworks.hw3.service;

import ru.otus.homeworks.hw3.domain.Answer;
import ru.otus.homeworks.hw3.domain.UserProfile;

import java.util.List;

public interface ReporterService {

    void showReport(UserProfile profile, List<Answer> answers);

}
