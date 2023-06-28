package ru.otus.homeworks.hw8.repositories.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.test.annotation.DirtiesContext;
import ru.otus.homeworks.hw8.entity.Author;
import ru.otus.homeworks.hw8.entity.Book;
import ru.otus.homeworks.hw8.entity.Genre;
import ru.otus.homeworks.hw8.repositories.BookRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

@DataMongoTest
@DisplayName("Репозиторий с книгами должен ")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class BookJpaRepositoryTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Autowired
    private BookRepository repository;

    @BeforeEach
    void fillData(@Autowired MongoTemplate mongoTemplate){
        val author1 = Author.builder().id("1L").name("автор1").build();
        val genre1 = Genre.builder().id("1L").name("жанр1").build();
        val book1 = Book.builder().id("1L").name("книга1").releaseYear((short)2000).author(author1).genre(genre1).build();
        mongoTemplate.save(author1);
        mongoTemplate.save(genre1);
        mongoTemplate.save(book1);
    }

    @Test
    @DisplayName("вернуть объект")
    void shouldGetEntity() {
        val expectedAuthor = new Author("1L", "автор1");
        val expectedGenre = new Genre("1L", "жанр1");
        val expectedBook = Book.builder()
                .id("1L")
                .name("книга1")
                .releaseYear((short) 2000)
                .author(expectedAuthor)
                .genre(expectedGenre)
                .build();
        val book = repository.findById("1L").orElseThrow();
        assertEquals(expectedBook, book);
    }

    @Test
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        repository.findAll().forEach(System.out::println);
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
        val book = Book.builder()
                .name("новая книга")
                .releaseYear((short) 2020)
                .author(author)
                .genre(genre)
                .build();
        repository.save(book);
        val savedBook = mongoTemplate.findById(book.getId(), Book.class);
        assertEquals(book, savedBook);
    }

    @Test
    @DisplayName("обновлять сущность")
    void shouldUpdateBook() {
        val book = mongoTemplate.findById("1L", Book.class);
        assertNotNull(book);
        val updatedBook = book.toBuilder()
                .name("обновленное навание")
                .releaseYear((short) 2009)
                .build();
        repository.save(updatedBook);
        val saved = mongoTemplate.findById("1L", Book.class);
        assertEquals(updatedBook, saved);
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
