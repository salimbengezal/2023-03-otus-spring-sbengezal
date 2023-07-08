package ru.otus.homeworks.hw9.service;

import ru.otus.homeworks.hw9.entity.Genre;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(String id) throws EntityNotFoundException;

    List<Genre> getAllByNameContains(String text);

}
