package ru.otus.homeworks.hw6.service;

import ru.otus.homeworks.hw6.entity.Author;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(long id) throws EntityNotFoundException;

}
