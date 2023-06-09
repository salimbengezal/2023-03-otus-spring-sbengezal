package ru.otus.homeworks.hw6.repositories;

import ru.otus.homeworks.hw6.entity.Author;

import java.util.List;
import java.util.Optional;

public interface AuthorRepository {

    List<Author> getAll();

    Optional<Author> getById(long id);

}
