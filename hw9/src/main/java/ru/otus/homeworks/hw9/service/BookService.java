package ru.otus.homeworks.hw9.service;

import ru.otus.homeworks.hw9.entity.Book;
import ru.otus.homeworks.hw9.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;

import java.util.List;

public interface BookService {

    List<Book> getAll();

    List<Book> getAllByNameContains(String text);

    Book deleteById(String id) throws EntityNotFoundException;

    Book getById(String id) throws EntityNotFoundException;

    Book update(String id, String name, Short releaseYear, String authorId, String genreId)
            throws EntityNotFoundException;

    Book add(String name, Short releaseYear, String authorId, String genreId)
            throws EntityNotFoundException, AtLeastOneParameterIsNullException;


}
