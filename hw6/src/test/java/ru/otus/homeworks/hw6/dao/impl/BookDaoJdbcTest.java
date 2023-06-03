package ru.otus.homeworks.hw6.dao.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw6.entity.Author;
import ru.otus.homeworks.hw6.entity.Book;
import ru.otus.homeworks.hw6.entity.Genre;

import static org.junit.jupiter.api.Assertions.assertEquals;

@JdbcTest
@Import(value = {BookDaoJdbc.class})
@DisplayName("Репозиторий с книгами должен ")
public class BookDaoJdbcTest {

    @Autowired
    private BookDaoJdbc bookDaoJdbc;

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expectedAuthor = new Author(1, "автор1");
        val expectedGenre = new Genre(1, "жанр1");
        val expectedBook = Book.builder()
                .id(1)
                .name("книга1")
                .releaseYear((short) 2000)
                .author(expectedAuthor)
                .genre(expectedGenre)
                .build();
        val book = bookDaoJdbc.getById(1).orElseThrow();
        assertEquals(expectedBook, book);
    }

    @Test
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        assertEquals(1, bookDaoJdbc.getAll().size());
    }

    @Test
    @DisplayName("вернуть пустой результат")
    void shouldReturnEmptyEntity() {
        assert (bookDaoJdbc.getById(-1).isEmpty());
    }

    @Test
    @DisplayName("добавляет новую сущность")
    void shouldAddNewBook() {
        val author = new Author(1, "автор1");
        val genre = new Genre(1, "жанр1");
        val newBook = Book.builder()
                .id(2)
                .name("новая книга")
                .releaseYear((short) 2020)
                .author(author)
                .genre(genre)
                .build();
        val count = bookDaoJdbc.getAll().size();
        bookDaoJdbc.add(newBook);
        assertEquals(count + 1, bookDaoJdbc.getAll().size());
        val addedBook = bookDaoJdbc.getById(2).orElseThrow();
        assertEquals(newBook, addedBook);
    }

    @Test
    @DisplayName("обновляет сущность")
    void shouldUpdateBook() {
        val count = bookDaoJdbc.getAll().size();
        val book = bookDaoJdbc.getById(1).orElseThrow();
        val updatedBook = book.toBuilder()
                .name("обновленное навание")
                .releaseYear((short) 2009)
                .build();
        bookDaoJdbc.update(updatedBook);
        assertEquals(count, bookDaoJdbc.getAll().size());
        val savedBook = bookDaoJdbc.getById(1).orElseThrow();
        assertEquals(updatedBook, savedBook);
    }

    @Test
    @DisplayName("удаляет сущность")
    void shouldDeleteBook() {
        val count = bookDaoJdbc.getAll().size();
        bookDaoJdbc.deleteById(1);
        assertEquals(count - 1, bookDaoJdbc.getAll().size());
    }

}
