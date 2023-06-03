package ru.otus.homeworks.hw5.dao;

import ru.otus.homeworks.hw5.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookDao {

    List<Book> getAll();

    Optional<Book> getById(long id);

    void deleteById(long id);

    Book add(Book book);

    void update(Book newBook);

}
