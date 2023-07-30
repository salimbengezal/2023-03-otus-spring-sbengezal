package ru.otus.homeworks.hw10.controller;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homeworks.hw10.dto.AuthorDtoResponse;
import ru.otus.homeworks.hw10.dto.BookDtoResponse;
import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.dto.GenreDtoResponse;
import ru.otus.homeworks.hw10.service.BookService;
import ru.otus.homeworks.hw10.service.CommentService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@DisplayName("Контроллер с действиями по комментариям должен ")
public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private BookService bookService;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("создавать новый комментарий")
    void shouldCreateComment() throws Exception {
        val comment = new CommentDtoRequest("useful-message", "1");
        mvc.perform(post("/comment")
                        .param("create", "")
                        .param("message", comment.getMessage())
                        .param("bookId", comment.getBookId())
                )
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string(HttpHeaders.LOCATION, "/book/" + comment.getBookId()));
        verify(commentService, times(1)).add(comment);
    }

    @Test
    @DisplayName("удалять новый комментарий")
    void shouldDeleteComment() throws Exception {
        val bookId = "some-book-id";
        val commentId = "some-comment-id";
        mvc.perform(post("/comment")
                        .param("delete", "")
                        .param("bookId", bookId)
                        .param("commentId", commentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string(HttpHeaders.LOCATION, "/book/%s".formatted(bookId)));
        verify(commentService, times(1)).deleteById(commentId);
    }

    @Test
    @DisplayName("проверять на валидность новый комментарий")
    void shouldValidateCreatingOfComment() throws Exception {
        val comment = new CommentDtoRequest("", "1");
        val genre = new GenreDtoResponse("1", "genre");
        val author = new AuthorDtoResponse("1", "author");
        val book = new BookDtoResponse(comment.getBookId(), "some_book", (short) 2020, author, genre);
        when(bookService.getById(comment.getBookId())).thenReturn(book);
        mvc.perform(post("/comment")
                        .param("create", "")
                        .param("message", comment.getMessage())
                        .param("bookId", comment.getBookId())
                )
                .andExpect(status().isOk())
                .andExpect(model().hasErrors())
                .andExpect(model().errorCount(2))
                .andExpect(view().name("book/details"));
    }

}
