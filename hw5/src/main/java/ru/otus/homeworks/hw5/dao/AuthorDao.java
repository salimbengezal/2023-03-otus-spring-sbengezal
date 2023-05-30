package ru.otus.homeworks.hw5.dao;

import ru.otus.homeworks.hw5.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    List<Author> getAll();

    Optional<Author> getById(long id);

}
