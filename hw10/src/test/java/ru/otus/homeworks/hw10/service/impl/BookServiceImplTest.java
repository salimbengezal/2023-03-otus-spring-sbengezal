package ru.otus.homeworks.hw10.service.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw10.dto.NewBookDtoRequest;
import ru.otus.homeworks.hw10.dto.UpdateBookDtoRequest;
import ru.otus.homeworks.hw10.entity.Author;
import ru.otus.homeworks.hw10.entity.Book;
import ru.otus.homeworks.hw10.entity.Genre;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.mapper.BookMapper;
import ru.otus.homeworks.hw10.repository.AuthorRepository;
import ru.otus.homeworks.hw10.repository.BookRepository;
import ru.otus.homeworks.hw10.repository.GenreRepository;
import ru.otus.homeworks.hw10.service.BookService;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
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

    @Autowired
    private BookMapper mapper;

    @Test
    @DisplayName("получать книги")
    void shouldGetAllBooks() {
        val author1 = new Author("1L", "a1");
        val author2 = new Author("2L", "a2");
        val genre1 = new Genre("1L", "g1");
        val genre2 = new Genre("2L", "g2");
        val books = List.of(
                new Book("1L", "b1", (short) 2000, author1, genre1),
                new Book("2L", "b2", (short) 2001, author2, genre2)
        );
        val dto = mapper.toDto(books);
        when(bookRepository.findAll()).thenReturn(books);
        assertEquals(bookService.getAll(), dto);
    }

    @Test
    @DisplayName("добавлять книгу")
    void shouldAddBook() throws EntityNotFoundException {
        val author = new Author("1L", "a1");
        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        val genre = new Genre("1L", "g1");
        when(genreRepository.findById(genre.getId())).thenReturn(Optional.of(genre));
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
        when(authorRepository.findById(author.getId())).thenReturn(Optional.of(author));
        val genre = new Genre("1L", "g1");
        when(genreRepository.findById(genre.getId())).thenReturn(Optional.of(genre));
        val book = new Book("1L", "newName", Double.valueOf(Math.random()).shortValue(), author, genre);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        when(bookRepository.save(book)).thenReturn(book);
        val bookDto = new UpdateBookDtoRequest(book.getId(), book.getName(), book.getReleaseYear(), author.getId(),
                genre.getId());
        bookService.update(bookDto);
        verify(bookRepository).save(book);
    }

    @Test
    @DisplayName("удалять книгу")
    void shouldDeleteBook() throws EntityNotFoundException {
        bookService.deleteById("1L");
        verify(bookRepository).deleteById("1L");
    }

}
