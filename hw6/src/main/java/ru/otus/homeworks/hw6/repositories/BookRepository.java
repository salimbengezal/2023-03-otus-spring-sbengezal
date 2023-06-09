package ru.otus.homeworks.hw6.repositories;

import ru.otus.homeworks.hw6.entity.Book;

import java.util.List;
import java.util.Optional;

public interface BookRepository {

    List<Book> getAll();

    Optional<Book> getById(long id);

    void delete(Book book);

    Book save(Book newBook);

}
