package ru.otus.homeworks.hw7.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import ru.otus.homeworks.hw7.entity.Author;
import ru.otus.homeworks.hw7.entity.Book;
import ru.otus.homeworks.hw7.entity.Genre;
import ru.otus.homeworks.hw7.repositories.BookRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataJpaTest
@DisplayName("Репозиторий с книгами должен ")
public class BookJpaRepositoryTest {

    @Autowired
    private TestEntityManager tem;

    @Autowired
    private BookRepository repository;

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
        val book = repository.findById(1L).orElseThrow();
        assertEquals(expectedBook, book);
    }

    @Test
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        assertEquals(1, repository.findAll().size());
    }

    @Test
    @DisplayName("вернуть пустой результат")
    void shouldReturnEmptyEntity() {
        assert (repository.findById(-1L).isEmpty());
    }

    @Test
    @DisplayName("добавлять новую сущность")
    void shouldAddNewBook() {
        val author = new Author(1, "автор1");
        val genre = new Genre(1, "жанр1");
        val book = Book.builder()
                .name("новая книга")
                .releaseYear((short) 2020)
                .author(author)
                .genre(genre)
                .build();
        repository.save(book);
        val savedBook = tem.find(Book.class, book.getId());
        assertEquals(book, savedBook);
    }

    @Test
    @DisplayName("обновлять сущность")
    void shouldUpdateBook() {
        val book = tem.find(Book.class, 1);
        assertNotNull(book);
        val updatedBook = book.toBuilder()
                .name("обновленное навание")
                .releaseYear((short) 2009)
                .build();
        repository.save(updatedBook);
        assertEquals(updatedBook, book);
    }

    @Test
    @DisplayName("удалять сущность")
    void shouldDeleteBook() {
        val book = tem.find(Book.class, 1);
        repository.delete(book);
        val deletedBook = tem.find(Book.class, 1);
        assertNull(deletedBook);
    }

}
