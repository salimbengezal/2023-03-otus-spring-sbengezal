package ru.otus.homeworks.hw6.service;

import ru.otus.homeworks.hw6.entity.Genre;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(long id) throws EntityNotFoundException;

}
