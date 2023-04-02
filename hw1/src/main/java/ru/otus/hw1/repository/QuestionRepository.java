package ru.otus.hw1.repository;

import ru.otus.hw1.domain.Question;

import java.util.List;

public interface QuestionRepository {

    List<Question> getAll();

}
