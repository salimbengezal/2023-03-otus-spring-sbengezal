package ru.otus.homeworks.hw2.repository;

import ru.otus.homeworks.hw2.domain.Question;
import ru.otus.homeworks.hw2.exceptions.QuestionReadingException;

import java.util.List;

public interface QuestionRepository {

    List<Question> getAll() throws QuestionReadingException;

}
