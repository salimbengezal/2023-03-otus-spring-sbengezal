package ru.otus.hw1.service;

import ru.otus.hw1.domain.Answer;

import java.util.List;

public interface FeedbackService {

    List<Answer> collect();

    void showAnswers(List<Answer> answers);

}
