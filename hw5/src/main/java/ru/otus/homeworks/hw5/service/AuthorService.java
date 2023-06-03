package ru.otus.homeworks.hw5.service;

import ru.otus.homeworks.hw5.entity.Author;
import ru.otus.homeworks.hw5.exceptions.EntityNotFoundException;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(long id) throws EntityNotFoundException;

}
