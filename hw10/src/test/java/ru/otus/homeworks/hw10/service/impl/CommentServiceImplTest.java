package ru.otus.homeworks.hw10.service.impl;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.dto.CommentDtoResponse;
import ru.otus.homeworks.hw10.entity.Book;
import ru.otus.homeworks.hw10.entity.Comment;
import ru.otus.homeworks.hw10.exceptions.EntityNotFoundException;
import ru.otus.homeworks.hw10.repositories.BookRepository;
import ru.otus.homeworks.hw10.repositories.CommentRepository;
import ru.otus.homeworks.hw10.service.CommentService;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.stream.IntStream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.*;

@SpringBootTest
@DisplayName("Сервис с комментариями должен ")
@Import(CommentServiceImpl.class)
public class CommentServiceImplTest {

    @MockBean
    private CommentRepository commentRepository;

    @MockBean
    private BookRepository bookRepository;

    @Autowired
    private CommentService commentService;

    @Test
    @DisplayName("получать все комментарии")
    void shouldGetAllComments() {
        val book = new Book("some-id", "some-name", (short) 2020, null, null);
        val comments = IntStream.rangeClosed(1, 3)
                .mapToObj(n -> new Comment("some-message" + n, book))
                .toList();
        val expectedComments = comments.stream()
                .map(comment -> new CommentDtoResponse(comment.getId(), comment.getMessage(), comment.getUpdateOn()))
                .toList();
        when(commentRepository.findAllByBookId(book.getId())).thenReturn(comments);
        assertEquals(commentService.getCommentsByBookId(book.getId()), expectedComments);
        verify(commentRepository, times(1)).findAllByBookId(book.getId());
    }

    @Test
    @DisplayName("удалять комментарий")
    void shouldRemoveComment() throws EntityNotFoundException {
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
        when(bookRepository.findById(commentDto.getBookId())).thenReturn(Optional.of(book));
        commentService.add(commentDto);
        verify(bookRepository, times(1)).findById(commentDto.getBookId());
        ArgumentCaptor<Comment> argumentCaptor = ArgumentCaptor.forClass(Comment.class);
        verify(commentRepository).save(argumentCaptor.capture());
        assert (argumentCaptor.getValue().getBook()).equals(book);
        assert (argumentCaptor.getValue().getMessage()).equals(commentDto.getMessage());
        assertNull(argumentCaptor.getValue().getId());
    }

}
