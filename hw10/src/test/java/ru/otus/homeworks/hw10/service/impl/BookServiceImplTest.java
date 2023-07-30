package ru.otus.homeworks.hw10.service.impl;

import lombok.val;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw10.dto.*;
import ru.otus.homeworks.hw10.entity.Author;
import ru.otus.homeworks.hw10.entity.Book;
import ru.otus.homeworks.hw10.entity.Genre;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.repository.AuthorRepository;
import ru.otus.homeworks.hw10.repository.BookRepository;
import ru.otus.homeworks.hw10.repository.GenreRepository;
import ru.otus.homeworks.hw10.service.BookService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
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
        val authorDto = new AuthorDtoResponse("1L", "a1");
        val genreDto = new GenreDtoResponse("1L", "g1");
        val expectedBookDto = new BookDtoResponse("1L", "b1", (short) 2000, authorDto, genreDto);
        assertEquals(bookService.getById("1L"), expectedBookDto);
        verify(bookRepository, times(1)).findById("1L");
    }

    @Test
    @DisplayName("вызывать исключение, если книги не существует")
    void shouldThrowEntityNotFoundException() {
        assertThrows(EntityNotFoundException.class, () -> bookService.getById("3"));
    }

    @Test
    @DisplayName("возвращать корректное количество книг")
    void shouldReturnCorrectLength() {
        assertEquals(2, bookService.getAll().size());
    }

    @Test
    @DisplayName("добавлять книгу")
    void shouldAddBook() throws EntityNotFoundException {
        val author = new Author("1L", "a1");
        val genre = new Genre("1L", "g1");
        val newBook = new Book(null, "name", Double.valueOf(Math.random()).shortValue(), author, genre);
        val savedBook = new Book("123L", newBook.getName(), newBook.getReleaseYear(), author, genre);
        when(bookRepository.save(newBook)).thenReturn(savedBook);
        val bookDto = new NewBookDtoRequest(newBook.getName(), newBook.getReleaseYear(), author.getId(), genre.getId());
        bookService.add(bookDto);
        verify(bookRepository, times(1)).save(newBook);
    }

    @Test
    @DisplayName("обновлять книгу")
    void shouldUpdateBook() throws EntityNotFoundException {
        val author = new Author("1L", "a1");
        val genre = new Genre("1L", "g1");
        val book = new Book("1L", "newName", Double.valueOf(Math.random()).shortValue(), author, genre);
        when(bookRepository.save(book)).thenReturn(book);
        val bookDto = new UpdateBookDtoRequest(book.getId(), book.getName(), book.getReleaseYear(), author.getId(), genre.getId());
        bookService.update(bookDto);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("удалять книгу")
    void shouldDeleteBook() throws EntityNotFoundException {
        val bookDto = bookService.getById("1L");
        bookService.deleteById(bookDto.id());
        verify(bookRepository).deleteById(bookDto.id());
    }

}
