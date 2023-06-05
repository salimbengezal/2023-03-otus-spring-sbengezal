package ru.otus.homeworks.hw6.service.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homeworks.hw6.entity.Author;
import ru.otus.homeworks.hw6.entity.Book;
import ru.otus.homeworks.hw6.entity.Genre;
import ru.otus.homeworks.hw6.exceptions.AtLeastOneParameterIsNullException;
import ru.otus.homeworks.hw6.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw6.repositories.impl.AuthorJpaRepository;
import ru.otus.homeworks.hw6.repositories.impl.BookJpaRepository;
import ru.otus.homeworks.hw6.repositories.impl.GenreJpaRepository;
import ru.otus.homeworks.hw6.service.BookService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
@DisplayName("Сервис с книгами должен ")
public class BookServiceImplTest {

    @MockBean
    private BookJpaRepository bookDaoJdbc;

    @MockBean
    private GenreJpaRepository genreDaoJdbc;

    @MockBean
    private AuthorJpaRepository authorDaoJdbc;

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

        when(bookDaoJdbc.getAll()).thenReturn(books);
        when(bookDaoJdbc.getById(1)).thenReturn(Optional.of(books.get(0)));
        when(bookDaoJdbc.getById(2)).thenReturn(Optional.of(books.get(1)));
        when(bookDaoJdbc.getById(3)).thenReturn(Optional.empty());

    }

    @Test
    @DisplayName("получать книгу")
    void shouldGetBook() throws EntityNotFoundException {
        val author1 = new Author(1, "a1");
        val genre = new Genre(1, "g1");
        val expectedBook = Book.builder().id(1).name("b1").releaseYear((short) 2000).author(author1).genre(genre).build();
        assertEquals(bookService.getById(1L), expectedBook);
        verify(bookDaoJdbc, times(1)).getById(1L);
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
        when(authorDaoJdbc.getById(1)).thenReturn(Optional.of(author));
        when(genreDaoJdbc.getById(1)).thenReturn(Optional.of(genre));
        when(bookDaoJdbc.save(newBook)).thenReturn(newBook);
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
        when(authorDaoJdbc.getById(1)).thenReturn(Optional.of(author));
        when(genreDaoJdbc.getById(1)).thenReturn(Optional.of(genre));
        when(bookDaoJdbc.getById(0)).thenReturn(Optional.of(book));
        bookService.update(
                book.getId(),
                book.getName(),
                book.getReleaseYear(),
                null,
                null);
        verify(bookDaoJdbc).save(book);
    }

    @Test
    @DisplayName("удалять книгу")
    void shouldDeleteBook() throws EntityNotFoundException {
        val book = bookService.getById(1);
        bookService.deleteById(1);
        verify(bookDaoJdbc).delete(book);
    }

}
