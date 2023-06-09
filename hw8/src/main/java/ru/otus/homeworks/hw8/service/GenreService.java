package ru.otus.homeworks.hw8.service;

import ru.otus.homeworks.hw8.entity.Genre;
import ru.otus.homeworks.hw8.exceptions.EntityNotFoundException;

import java.util.List;

public interface GenreService {

    List<Genre> getAll();

    Genre getById(String id) throws EntityNotFoundException;

    List<Genre> getAllByNameContains(String text);

}
