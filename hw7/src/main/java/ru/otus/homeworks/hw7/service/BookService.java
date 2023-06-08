package ru.otus.homeworks.hw7.service;

import ru.otus.homeworks.hw7.entity.Book;
import ru.otus.homeworks.hw7.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw7.exceptions.EntityNotFoundException;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    void deleteById(long id) throws EntityNotFoundException;

    Book getById(long id) throws EntityNotFoundException;

    Book update(long id, String name, Short releaseYear, Long authorId, Long genreId) throws EntityNotFoundException;

    Book add(String name, Short releaseYear, Long authorId, Long genreId)
            throws EntityNotFoundException, AtLeastOneParameterIsNullException;

}
