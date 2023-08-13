package ru.otus.homeworks.hw10.service.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homeworks.hw10.entity.Author;
import ru.otus.homeworks.hw10.entity.Book;
import ru.otus.homeworks.hw10.entity.Comment;
import ru.otus.homeworks.hw10.entity.Genre;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.mapper.BookMapper;
import ru.otus.homeworks.hw10.repository.BookRepository;
import ru.otus.homeworks.hw10.repository.CommentRepository;
import ru.otus.homeworks.hw10.service.BookDetailsService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Сервис с книгами должен ")
public class BookDetailsServiceImplTest {

    @MockBean
    private BookRepository bookRepository;

    @MockBean
    private CommentRepository commentRepository;

    @Autowired
    private BookDetailsService bookDetailsService;

    @Autowired
    private BookMapper mapper;

    @Test
    @DisplayName("получать книгу")
    void shouldGetBook() throws EntityNotFoundException {
        val author = new Author("1L", "a1");
        val genre = new Genre("1L", "g1");
        val book = new Book("b_id", "b_name", (short) 2000, author, genre);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        val comments = IntStream.rangeClosed(1, 4)
                .mapToObj(number ->
                        new Comment("id" + number, "message" + number, book, LocalDateTime.now()))
                .toList();
        when(commentRepository.findAllByBookId(book.getId())).thenReturn(comments);
        val dto = mapper.toDto(book, comments);
        assertEquals(bookDetailsService.getById(book.getId()), dto);
        verify(bookRepository, times(1)).findById(book.getId());
        verify(commentRepository, times(1)).findAllByBookId(book.getId());
    }

    @Test
    @DisplayName("вызывать исключение, если книги не существует")
    void shouldThrowEntityNotFoundException() {
        when(bookRepository.findById("-1")).thenReturn(Optional.empty());
        assertThrows(EntityNotFoundException.class, () -> bookDetailsService.getById("-1"));
    }

}
