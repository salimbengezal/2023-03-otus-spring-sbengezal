package ru.otus.homeworks.hw3.repository;

import ru.otus.homeworks.hw3.domain.Question;

import java.util.List;

public interface QuestionRepository {

    List<Question> getAll();

}
