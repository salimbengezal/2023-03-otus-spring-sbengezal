package ru.otus.homeworks.hw6.repository.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw6.entity.Author;
import ru.otus.homeworks.hw6.entity.Book;
import ru.otus.homeworks.hw6.entity.Genre;
import ru.otus.homeworks.hw6.repositories.impl.BookJpaRepository;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
@Import(value = {BookJpaRepository.class})
@DisplayName("Репозиторий с книгами должен ")
public class BookJpaRepositoryTest {

    @Autowired
    private BookJpaRepository repository;

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
        val book = repository.getById(1).orElseThrow();
        assertEquals(expectedBook, book);
    }

    @Test
    @DisplayName("вернуть верное количество")
    void shouldReturnExpectedCount() {
        assertEquals(1, repository.getAll().size());
    }

    @Test
    @DisplayName("вернуть пустой результат")
    void shouldReturnEmptyEntity() {
        assert (repository.getById(-1).isEmpty());
    }

    @Test
    @DisplayName("добавлять новую сущность")
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
        val count = repository.getAll().size();
        repository.save(newBook);
        assertEquals(count + 1, repository.getAll().size());
        val addedBook = repository.getById(2).orElseThrow();
        assertEquals(newBook, addedBook);
    }

    @Test
    @DisplayName("обновлять сущность")
    void shouldUpdateBook() {
        val count = repository.getAll().size();
        val book = repository.getById(1).orElseThrow();
        val updatedBook = book.toBuilder()
                .name("обновленное навание")
                .releaseYear((short) 2009)
                .build();
        repository.save(updatedBook);
        assertEquals(count, repository.getAll().size());
        val savedBook = repository.getById(1).orElseThrow();
        assertEquals(updatedBook, savedBook);
    }

    @Test
    @DisplayName("удалять сущность")
    void shouldDeleteBook() {
        val count = repository.getAll().size();
        repository.deleteById(1);
        assertEquals(count - 1, repository.getAll().size());
    }

}
