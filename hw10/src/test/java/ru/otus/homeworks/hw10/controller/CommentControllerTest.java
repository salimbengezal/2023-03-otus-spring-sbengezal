package ru.otus.homeworks.hw10.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.val;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import ru.otus.homeworks.hw10.dto.CommentDtoRequest;
import ru.otus.homeworks.hw10.service.CommentService;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(CommentController.class)
@DisplayName("Контроллер с действиями по комментариям должен ")
public class CommentControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private CommentService commentService;

    @Autowired
    private ObjectMapper mapper;

    @Test
    @DisplayName("создавать новый комментарий")
    void shouldCreateComment() throws Exception {
        val comment = new CommentDtoRequest("useful-message", "1");
        val commentJson = mapper.writeValueAsString(comment);
        mvc.perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commentJson))
                .andExpect(status().isOk());
        verify(commentService, times(1)).add(comment);
    }

    @Test
    @DisplayName("удалять новый комментарий")
    void shouldDeleteComment() throws Exception {
        val id = "comment-id";
        mvc.perform(delete("/api/comment/%s".formatted(id)))
                .andExpect(status().isOk());
        verify(commentService, times(1)).deleteById(id);
    }

    @Test
    @DisplayName("проверять на валидность новый комментарий")
    void shouldValidateCreatingOfComment() throws Exception {
        val comment = new CommentDtoRequest("", "1");
        val commentJson = mapper.writeValueAsString(comment);
        mvc.perform(post("/api/comment")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(commentJson)
                )
                .andExpect(status().isBadRequest());
        verify(commentService, never()).add(comment);
    }

}
