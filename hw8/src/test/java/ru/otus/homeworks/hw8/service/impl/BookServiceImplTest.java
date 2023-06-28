package ru.otus.homeworks.hw8.service.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homeworks.hw8.entity.Author;
import ru.otus.homeworks.hw8.entity.Book;
import ru.otus.homeworks.hw8.entity.Genre;
import ru.otus.homeworks.hw8.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw8.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw8.repositories.AuthorRepository;
import ru.otus.homeworks.hw8.repositories.BookRepository;
import ru.otus.homeworks.hw8.repositories.GenreRepository;
import ru.otus.homeworks.hw8.service.BookService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Сервис с книгами должен ")
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
        val author1 = new Author(1, "a1");
        val author2 = new Author(2, "a2");
        val genre = new Genre(1, "g1");
        val books = List.of(
                Book.builder().id(1).name("b1").releaseYear((short) 2000).author(author1).genre(genre).build(),
                Book.builder().id(2).name("b2").releaseYear((short) 2001).author(author2).genre(genre).build()
        );

        when(bookRepository.findAll()).thenReturn(books);
        when(bookRepository.findById(1L)).thenReturn(Optional.of(books.get(0)));
        when(bookRepository.findById(2L)).thenReturn(Optional.of(books.get(1)));
        when(bookRepository.findById(3L)).thenReturn(Optional.empty());

    }

    @Test
    @DisplayName("получать книгу")
    void shouldGetBook() throws EntityNotFoundException {
        val author1 = new Author(1, "a1");
        val genre = new Genre(1, "g1");
        val expectedBook = Book.builder().id(1).name("b1").releaseYear((short) 2000).author(author1).genre(genre).build();
        assertEquals(bookService.getById(1L), expectedBook);
        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    @DisplayName("вызывать исключение, если книги не существует")
    void shouldThrow() {
        assertThrows(EntityNotFoundException.class, () -> bookService.getById(3));
    }

    @Test
    @DisplayName("возвращать корректное количество книг")
    void shouldReturnCorrectLength() {
        assertEquals(2, bookService.getAll().size());
    }

    @Test
    @DisplayName("добавлять книгу")
    void shouldAddBook() throws EntityNotFoundException, AtLeastOneParameterIsNullException {
        val author = new Author(1, "a");
        val genre = new Genre(1, "g");
        val newBook = Book.builder().genre(genre).author(author).releaseYear((short) 2020).name("name").build();
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
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
        val author = new Author(1, "a");
        val genre = new Genre(1, "g");
        val book = Book.builder().genre(genre).author(author).releaseYear((short) 2020).name("newName").build();
        when(authorRepository.findById(1L)).thenReturn(Optional.of(author));
        when(genreRepository.findById(1L)).thenReturn(Optional.of(genre));
        when(bookRepository.findById(0L)).thenReturn(Optional.of(book));
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
        val book = bookService.getById(1L);
        bookService.deleteById(1);
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
