package ru.otus.homeworks.hw8.service;

import ru.otus.homeworks.hw8.entity.Author;
import ru.otus.homeworks.hw8.exceptions.EntityNotFoundException;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(String id) throws EntityNotFoundException;

    List<Author> getAllByNameContains(String text);
}
