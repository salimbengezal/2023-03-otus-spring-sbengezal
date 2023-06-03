package ru.otus.homeworks.hw5.service;

import ru.otus.homeworks.hw5.entity.Genre;
import ru.otus.homeworks.hw5.exceptions.EntityNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(long id) throws EntityNotFoundException;

}
