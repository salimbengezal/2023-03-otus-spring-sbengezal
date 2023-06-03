package ru.otus.homeworks.hw6.dao;

import ru.otus.homeworks.hw6.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorDao {

    List<Author> getAll();

    Optional<Author> getById(long id);

}
