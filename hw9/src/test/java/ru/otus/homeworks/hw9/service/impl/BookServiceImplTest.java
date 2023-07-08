package ru.otus.homeworks.hw9.service.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw9.entity.Author;
import ru.otus.homeworks.hw9.entity.Book;
import ru.otus.homeworks.hw9.entity.Genre;
import ru.otus.homeworks.hw9.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw9.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw9.repositories.AuthorRepository;
import ru.otus.homeworks.hw9.repositories.BookRepository;
import ru.otus.homeworks.hw9.repositories.GenreRepository;
import ru.otus.homeworks.hw9.service.BookService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@DataMongoTest
@DisplayName("Сервис с книгами должен ")
@Import({BookServiceImpl.class, AuthorServiceImpl.class, GenreServiceImpl.class, CommentServiceImpl.class})
public class BookServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private GenreRepository genreRepository;

    @MockBean
    private AuthorRepository authorRepository;

    @Autowired
    private BookService bookService;

    @BeforeEach
    void fillData() {
        val author1 = new Author("1L", "a1");
        val author2 = new Author("2L", "a2");
        val genre1 = new Genre("1L", "g1");
        val genre2 = new Genre("2L", "g2");
        val books = List.of(
                new Book("1L", "b1", (short) 2000, author1, genre1),
                new Book("2L", "b2", (short) 2001, author2, genre1)
        );
        when(genreRepository.findAll()).thenReturn(List.of(genre1, genre2));
        when(authorRepository.findAll()).thenReturn(List.of(author1, author2));
        when(bookRepository.findAll()).thenReturn(books);
        when(genreRepository.findById("1L")).thenReturn(Optional.of(genre1));
        when(genreRepository.findById("2L")).thenReturn(Optional.of(genre2));
        when(authorRepository.findById("1L")).thenReturn(Optional.of(author1));
        when(authorRepository.findById("2L")).thenReturn(Optional.of(author2));
        when(bookRepository.findById("1L")).thenReturn(Optional.of(books.get(0)));
        when(bookRepository.findById("2L")).thenReturn(Optional.of(books.get(1)));
        when(bookRepository.findById("3L")).thenReturn(Optional.empty());

    }

    @Test
    @DisplayName("получать книгу")
    void shouldGetBook() throws EntityNotFoundException {
        val author1 = new Author("1L", "a1");
        val genre1 = new Genre("1L", "g1");
        val expectedBook = new Book("1L", "b1", (short) 2000, author1, genre1);
        assertEquals(bookService.getById("1L"), expectedBook);
        verify(bookRepository, times(1)).findById("1L");
    }

    @Test
    @DisplayName("вызывать исключение, если книги не существует")
    void shouldThrow() {
        assertThrows(EntityNotFoundException.class, () -> bookService.getById("3"));
    }

    @Test
    @DisplayName("возвращать корректное количество книг")
    void shouldReturnCorrectLength() {
        assertEquals(2, bookService.getAll().size());
    }

    @Test
    @DisplayName("добавлять книгу")
    void shouldAddBook() throws EntityNotFoundException, AtLeastOneParameterIsNullException {
        val author = new Author("1L", "a");
        val genre = new Genre("1L", "g");
        val newBook = new Book("name", (short) 2020, author, genre);
        when(authorRepository.findById("1L")).thenReturn(Optional.of(author));
        when(genreRepository.findById("1L")).thenReturn(Optional.of(genre));
        when(bookRepository.save(newBook)).thenReturn(newBook);
        val book = bookService.add(
                newBook.getName(),
                newBook.getReleaseYear(),
                newBook.getAuthor().getId(),
                newBook.getGenre().getId());
        assertEquals(book, newBook);
    }

    @Test
    @DisplayName("обновлять книгу")
    void shouldUpdateBook() throws EntityNotFoundException {
        val author = new Author("1L", "a");
        val genre = new Genre("1L", "g");
        val book = bookRepository.findById("1L").orElseThrow();
        when(authorRepository.findById("1L")).thenReturn(Optional.of(author));
        when(genreRepository.findById("1L")).thenReturn(Optional.of(genre));
        when(bookRepository.findById("0L")).thenReturn(Optional.of(book));
        bookService.update(
                book.getId(),
                book.getName(),
                book.getReleaseYear(),
                null,
                null);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("удалять книгу")
    void shouldDeleteBook() throws EntityNotFoundException {
        val book = bookService.getById("1L");
        bookService.deleteById("1L");
        verify(bookRepository).delete(book);
    }

    @Test
    @DisplayName("вызывать исключение из-за недостаточного количества аргументов")
    void shouldThrowAtLeastOneParameterIsNullException() {
        assertThrows(
                AtLeastOneParameterIsNullException.class,
                () -> bookService.add("some_name", null, null, null)
        );
    }

}
