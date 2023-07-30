package ru.otus.homeworks.hw9.repositories.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.homeworks.hw9.entity.Author;
import ru.otus.homeworks.hw9.entity.Book;
import ru.otus.homeworks.hw9.entity.Genre;
import ru.otus.homeworks.hw9.repositories.BookRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataMongoTest
@DisplayName("Репозиторий с книгами должен ")
public class BookMongoRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository repository;

    @BeforeEach
    void fillData(@Autowired MongoTemplate mongoTemplate) {
        val author1 = new Author("1L", "автор1");
        val genre1 = new Genre("1L", "жанр1");
        val book1 = new Book("1L", "книга1", (short) 2000, author1, genre1);
        mongoTemplate.save(author1);
        mongoTemplate.save(genre1);
        mongoTemplate.save(book1);
    }

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expectedAuthor = new Author("1L", "автор1");
        val expectedGenre = new Genre("1L", "жанр1");
        val expectedBook = new Book("1L", "книга1", (short) 2000, expectedAuthor, expectedGenre);
        val book = repository.findById("1L").orElseThrow();
        assertEquals(expectedBook, book);
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.BEFORE_METHOD)
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        assertEquals(1, repository.findAll().size());
    }

    @Test
    @DisplayName("вернуть пустой результат")
    void shouldReturnEmptyEntity() {
        assert (repository.findById("-1L").isEmpty());
    }

    @Test
    @DisplayName("добавлять новую сущность")
    void shouldAddNewBook() {
        val author = new Author("1L", "автор1");
        val genre = new Genre("1L", "жанр1");
        val book = new Book("новая книга", (short) 2020, author, genre);
        repository.save(book);
        val savedBook = mongoTemplate.findById(book.getId(), Book.class);
        assertEquals(book, savedBook);
    }

    @Test
    @DisplayName("обновлять сущность")
    void shouldUpdateBook() {
        val book = mongoTemplate.findById("1L", Book.class);
        assertNotNull(book);
        book.setName("обновленное навание");
        book.setReleaseYear((short) 2009);
        repository.save(book);
        val saved = mongoTemplate.findById("1L", Book.class);
        assertEquals(book, saved);
    }

    @Test
    @DisplayName("удалять сущность")
    void shouldDeleteBook() {
        val book = mongoTemplate.findById("1L", Book.class);
        assertNotNull(book);
        repository.delete(book);
        val deletedBook = mongoTemplate.findById("1L", Book.class);
        assertNull(deletedBook);
    }

}
