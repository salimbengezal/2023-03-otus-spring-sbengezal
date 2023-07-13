package ru.otus.homeworks.hw9.controllers;

import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homeworks.hw9.dto.NewCommentDtoRequest;
import ru.otus.homeworks.hw9.service.CommentService;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.header;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CommentController.class)
@DisplayName("Контроллер с действиями по комментариям должен ")
public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService commentService;

    @Test
    @DisplayName("создавать новый комментарий")
    void shouldCreateComment() throws Exception {
        val bookId = "1";
        val comment = new NewCommentDtoRequest("useful-message", bookId);
        commentService.add(comment);
        mvc.perform(post("/book/%s/add-comment".formatted(bookId))
                        .content(comment.message())
                        .param("bookId", comment.bookId()))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string(HttpHeaders.LOCATION, "/book/" + bookId));
        verify(commentService, times(1)).add(comment);
    }

    @Test
    @DisplayName("удалять новый комментарий")
    void shouldRemoveComment() throws Exception {
        val bookId = "some-book-id";
        val commentId = "some-comment-id";
        mvc.perform(get("/book/%s/delete-comment".formatted(bookId))
                        .param("id", commentId)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is3xxRedirection())
                .andExpect(header().string(HttpHeaders.LOCATION, "/book/%s".formatted(bookId)));
        verify(commentService, times(1)).deleteById(commentId);
    }

}
