package ru.otus.homeworks.hw6.repositories;

import ru.otus.homeworks.hw6.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreRepository {

    List<Genre> getAll();

    Optional<Genre> getById(long id);
}
