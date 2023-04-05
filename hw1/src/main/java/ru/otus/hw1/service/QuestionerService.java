package ru.otus.hw1.service;

import ru.otus.hw1.domain.Answer;
import ru.otus.hw1.domain.Question;

public interface QuestionerService {

    Answer getAnswerByQuestion(Question question);

}
