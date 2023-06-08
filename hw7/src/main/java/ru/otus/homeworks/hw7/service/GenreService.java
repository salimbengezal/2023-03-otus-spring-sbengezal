package ru.otus.homeworks.hw7.service;

import ru.otus.homeworks.hw7.entity.Genre;
import ru.otus.homeworks.hw7.exceptions.EntityNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(long id) throws EntityNotFoundException;

}
