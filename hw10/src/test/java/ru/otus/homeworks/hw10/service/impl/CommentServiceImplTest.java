package ru.otus.homeworks.hw10.service.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.entity.Book;
import ru.otus.homeworks.hw10.entity.Comment;
import ru.otus.homeworks.hw10.exception.EntityNotFoundException;
import ru.otus.homeworks.hw10.repository.BookRepository;
import ru.otus.homeworks.hw10.repository.CommentRepository;
import ru.otus.homeworks.hw10.service.CommentService;

import java.time.LocalDateTime;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Сервис с комментариями должен ")
public class CommentServiceImplTest {

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("удалять комментарий")
    void shouldRemoveComment() {
        val book = new Book("some-id", "some-name", (short) 2020, null, null);
        val comment = new Comment("2", "some-message", book, LocalDateTime.now());
        when(commentRepository.findById(comment.getId())).thenReturn(Optional.of(comment));
        commentService.deleteById(comment.getId());
        verify(commentRepository, times(1)).deleteById(comment.getId());
    }

    @Test
    @DisplayName("добавлять комментарий")
    void shouldAddComment() throws EntityNotFoundException {
        val commentDto = new CommentDtoRequest("some-message", "some-book-id");
        val book = new Book(commentDto.getBookId(), "some-book", (short) 123, null, null);
        when(bookRepository.findById(book.getId())).thenReturn(Optional.of(book));
        commentService.add(commentDto);
        ArgumentCaptor<Comment> argumentCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(commentRepository).save(argumentCaptor.capture());
        assert (argumentCaptor.getValue().getBook()).equals(book);
        assert (argumentCaptor.getValue().getMessage()).equals(commentDto.getMessage());
        assertNull(argumentCaptor.getValue().getId());
        verify(bookRepository, times(1)).findById(book.getId());
    }

}
