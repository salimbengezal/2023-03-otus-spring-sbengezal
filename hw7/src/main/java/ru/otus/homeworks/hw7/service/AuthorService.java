package ru.otus.homeworks.hw7.service;

import ru.otus.homeworks.hw7.entity.Author;
import ru.otus.homeworks.hw7.exceptions.EntityNotFoundException;

import java.util.List;

public interface AuthorService {

    List<Author> getAll();

    Author getById(long id) throws EntityNotFoundException;

}
