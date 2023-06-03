package ru.otus.homeworks.hw6.dao;

import ru.otus.homeworks.hw6.entity.Genre;

import java.util.List;
import java.util.Optional;

public interface GenreDao {

    List<Genre> getAll();

    Optional<Genre> getById(long id);
}
