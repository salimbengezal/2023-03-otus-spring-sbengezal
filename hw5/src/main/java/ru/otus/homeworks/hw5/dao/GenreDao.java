package ru.otus.homeworks.hw5.dao;

import ru.otus.homeworks.hw5.entity.Genre;

import java.util.List;

public interface GenreDao {

    List<Genre> getAll();

    Genre getById(long id);
}
