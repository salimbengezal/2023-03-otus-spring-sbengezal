package ru.otus.homeworks.hw4.repository;

import ru.otus.homeworks.hw4.domain.Question;

import java.util.List;

public interface QuestionRepository {

    List<Question> getAll();

}
